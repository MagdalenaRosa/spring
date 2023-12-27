package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProductController {
    
    ProductService pService;
    ProductController(ProductService pService){
        this.pService=pService;
    }


    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "Products");
        model.addAttribute("db", pService.getDatabase());
        return "product/products";
    }


    @PostMapping("/saveProduct")// metodą post by dane były chronione
    // tu te @requestparam muszą być takie jak w modelu Product nie inne !!! -> lepiej żeby przyjmowało obiekt product
    public String saveProduct(Product productform, Model model) {
        model.addAttribute("action", "/saveProduct");
       
        pService.insertProduct(productform);
        return "redirect:/";
    }

    @GetMapping("/removeProduct")
    public String removeProduct(@RequestParam Integer productId) {
        pService.deleteProduct(productId);
        return "redirect:/";
    }

    @GetMapping("/productDetails")
    public String showProductDetail(@RequestParam Integer productId, Model model) {
    optionalProduct(productId, model);
        return "product/product-detail";
    }
    @GetMapping("/editProduct")
    public String showEditProductForm(@RequestParam Integer productId, Model model) {
        model.addAttribute("action", "/editedProduct?productId=" + productId);
        optionalProduct(productId, model);
        return "product/edit-product";
    }

    @PostMapping("/editedProduct") 
    public String saveEditedProduct(Product productFrom, @RequestParam Integer productId) {
        pService.editProduct(productFrom, productId);
        return "redirect:/";
    }



private void optionalProduct(@RequestParam Integer productId, Model model){
    var optionalProduct = pService.findProduct(productId);
    if (optionalProduct.isPresent()) {
            var product = optionalProduct.get();
            model.addAttribute("product", product);
             model.addAttribute("title", "Product");
             

    
    }}}