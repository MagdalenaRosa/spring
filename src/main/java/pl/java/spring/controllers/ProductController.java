package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    List<Product> database = new ArrayList<>();
    ProductController() {
        database.add(new Product(1, "iPhone X", "", "", BigDecimal.valueOf(10.99)));
        database.add(new Product(3, "iPhone 11", "", "", BigDecimal.valueOf(15.10)));
        database.add(new Product(6, "iPhone 12", "", "", BigDecimal.valueOf(20.33)));
        database.add(new Product(9, "iPhone 13", "", "", BigDecimal.valueOf(17.77)));
    }

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "Product List");
        model.addAttribute("db", database);
        return "Products";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(Product productFrom) {
        var productExists = database.stream()
                .map(Product::getName)
                .anyMatch(dbProductName -> productFrom.getName().equals(dbProductName));
        if (!productExists) {
            var lastIndex = database.size() - 1;
            var nextId = database.get(lastIndex).getId() + 1;
            var product = new Product(nextId, productFrom.getName(), productFrom.getDesc(),
                    productFrom.getImgUri(), productFrom.getPrice());
            database.add(product);
        }
        return "redirect:/";
    }

    @GetMapping("/removeProduct")
    public String removeProduct(@RequestParam Integer productId) {
        database.removeIf(dbProduct -> dbProduct.getId().equals(productId));
        return "redirect:/";
    }
}

