package com.example.demo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Product;
import com.example.demo.services.ProductCategoryService;
import com.example.demo.services.ProductService;

@RequiredArgsConstructor
@Controller
public class ProductController {

    final ProductService productService;
    final ProductCategoryService productCategoryService;

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "Products");
        model.addAttribute("db", productService.findAllProducts());
        model.addAttribute("actionUri", "/saveProduct");
        model.addAttribute("categories", productCategoryService.findAllCategoryProducts());
        return "product/products";
    }

    @GetMapping("/productDetails/{productId}")
    public String productDetails(@PathVariable Integer productId, Model model) {
        model.addAttribute("id", productId);
        bindProductToModel(productId, model);
        return "product/product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product productFrom, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("product", productFrom);
        } else {
            productService.insertProduct(productFrom);
        }
        return "redirect:/";
    }

    @GetMapping("/editProduct/{productId}")
    public String showEditProductForm(@PathVariable Integer productId, Model model) {
        model.addAttribute("actionUri", "/editedProduct/" + productId);
        model.addAttribute("categories", productCategoryService.findAllCategoryProducts());
        bindProductToModel(productId, model);
        return "product/edit-product";
    }

    @PostMapping("/editedProduct/{productId}") // /editedProduct/4
    public String saveEditedProduct(@PathVariable Integer productId, @Valid Product productFrom,
            BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            return "redirect:/editProduct/" + productId;
        } else {
            productService.updateProduct(productFrom, productId);
            return "redirect:/";
        }
    }

    @GetMapping("/removeProduct/{productId}")
    public String removeProduct(@PathVariable Integer productId) {
        productService.removeProduct(productId);
        return "redirect:/";
    }

    private void bindProductToModel(Integer productId, Model model) {
        var optionalProduct = productService.findProductById(productId);
        if (optionalProduct.isEmpty()) {
            // todo: nie odnalazłem elementu!
        } else {
            var product = optionalProduct.get();
            model.addAttribute("product", product);
        }
    }

}
