package com.example.demo.controllers;

import com.example.demo.models.ProductCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductCategoryController {
    List<ProductCategory> productCategoryList = new ArrayList<>();

    ProductCategoryController() {
        productCategoryList.add(new ProductCategory(1, "phones", "", ""));
        productCategoryList.add(new ProductCategory(2, "tablets", "", ""));
        productCategoryList.add(new ProductCategory(3, "Tv", "", ""));

    }

    @GetMapping("/categories")
    public String showProductCategory(Model model) {
        model.addAttribute("title", "Category List");
        model.addAttribute("categoryDB", productCategoryList);

        return "categories";
    }

    @PostMapping("/saveCategory")
    public String saveProductCategory(ProductCategory productCategoryForm) {
        var categoryExist = productCategoryList.stream().anyMatch(category -> category.getName().equals(productCategoryForm.getName()));
        if (!categoryExist) {
            var nextID = 1;
            if (!productCategoryList.isEmpty()) {
                var lastInddex = productCategoryList.size() - 1;
                nextID = productCategoryList.get(lastInddex).getId() + 1;
            }

            var category = new ProductCategory(nextID, productCategoryForm.getName(), productCategoryForm.getDesc(), productCategoryForm.getImgUri());
            productCategoryList.add(category);
        }
        return "redirect:/categories";
    }

    @GetMapping("/removeCategory")
    public String removeProductCategory(@RequestParam Integer categoryID) {
        productCategoryList.removeIf(category -> category.getId().equals(categoryID));
        return "redirect:/categories";
    }

    @GetMapping("/categoryDetail")
    public String showCategoryDetail(@RequestParam Integer categoryID, Model model) {
        var optionalCategoryList = productCategoryList.stream()
                .filter(category -> category.getId().equals(categoryID))
                .findFirst();

        if (optionalCategoryList.isPresent()) {
  var category =optionalCategoryList.get();
            model.addAttribute("category", category);
        } else {
          
        }

        model.addAttribute("title", "Category detail");

        return "/category-detail";

    }

}
