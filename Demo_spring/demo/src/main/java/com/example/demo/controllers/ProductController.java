package com.example.demo.controllers;

import com.example.demo.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    List<Product> database = new ArrayList<>();

    ProductController() {
        database.add(new Product(1, "Iphone 11", "", "", BigDecimal.valueOf(345.87)));
        database.add(new Product(3, "Iphone 12", "", "", BigDecimal.valueOf(345.87)));
        database.add(new Product(13, "Iphone 13", "", "", BigDecimal.valueOf(345.87)));
        database.add(new Product(14, "Iphone 15", "", "", BigDecimal.valueOf(345.87)));

    }

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "Products");
        model.addAttribute("db", database);
        return "products";
    }


    @PostMapping("/saveProduct")// metodą post by dane były chronione
    // tu te @requestparam muszą być takie jak w modelu Product nie inne !!! -> lepiej żeby przyjmowało obiekt product
    public String saveProduct(Product productform) {
        var productExist = database.stream().anyMatch(product -> product.getName().equals(productform.getName()));
        if (!productExist) {
            var nextId = 1;
            if (!database.isEmpty()) {
                var lastIndex = database.size() - 1;
                nextId = database.get(lastIndex).getId() + 1;
            }

            var product = new Product(nextId, productform.getName(), productform.getDesc(), productform.getUrlUri(), productform.getPrice());
            database.add(product);
        }
        return "redirect:/";
    }

    @GetMapping("/removeProduct")
    public String removeProduct(@RequestParam Integer productId) {
        database.removeIf(dbProduct -> dbProduct.getId().equals(productId));
        return "redirect:/";
    }

    @GetMapping("/productDetails")
    public String showProductDetail(@RequestParam Integer productId, Model model) {
        var optionalProduct = database.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
        if (optionalProduct.isPresent()) {
            var product = optionalProduct.get();
            model.addAttribute("product", product);
        } else {
           
        }
        model.addAttribute("title", "Product detail");

        return "/product-detail";
    }


}
