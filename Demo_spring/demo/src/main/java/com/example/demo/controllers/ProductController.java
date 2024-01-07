package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class ProductController {

    final ProductService productService;

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "Products");
        model.addAttribute("db", productService.findAllProducts());
        model.addAttribute("action", "/saveProduct");
        return "product/products";
    }

    @PostMapping("/saveProduct") // metodą post by dane były chronione
    // tu te @requestparam muszą być takie jak w modelu Product nie inne !!! ->
    // lepiej żeby przyjmowało obiekt product
    public String saveProduct(@Valid Product productform, BindingResult bindingResult,
            RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("product", productform);

        } else {

            productService.insertProduct(productform);
        }

        return "redirect:/";
    }

    @GetMapping("/productDetails/{productId}")
    public String showProductDetail(@PathVariable Integer productId, Model model) {
        model.addAttribute("id", productId);
        optionalProduct(productId, model);
        return "product/product-detail";
    }

    @GetMapping("/removeProduct/{productId}")
    public String removeProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return "redirect:/";
    }

    @GetMapping("/editProduct/{productId}")
    public String showEditProductForm(@PathVariable Integer productId, Model model) {
        model.addAttribute("action", "/editedProduct/" + productId);
        optionalProduct(productId, model);
        return "product/edit-product";
    }

    @PostMapping("/editedProduct/{productId}")
    public String saveEditedProduct(@PathVariable Integer productId, @Valid Product productform,
            BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("product", productform);
            return "redirect:/editProduct/" + productId;
        } else {
            productService.updateProduct(productform, productId);
            return "redirect:/";

        }
    }

    private void optionalProduct(@RequestParam Integer productId, Model model) {
        var optionalProduct = productService.findProduct(productId);
        if (optionalProduct.isPresent()) {
            var product = optionalProduct.get();
            model.addAttribute("product", product);
            model.addAttribute("title", "Product");

        }
    }
}