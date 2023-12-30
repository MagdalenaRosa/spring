package com.example.demo.controllers;

import com.example.demo.models.ProductCategory;
import com.example.demo.services.ProductCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductCategoryController {

    ProductCategoryService service;

    ProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public String showProductCategory(Model model) {
        model.addAttribute("title", "Category List");
        model.addAttribute("categoryDB", service.findAllCategories());
        model.addAttribute("action", "/saveCategory");

        return "category/categories";
    }

    @PostMapping("/saveCategory")
    public String saveProductCategory(ProductCategory productCategoryForm) {
        service.insertCategory(productCategoryForm);
        return "redirect:/categories";
    }

    @GetMapping("/categoryDetail/{categoryID}")
    public String showCategoryDetail(@PathVariable Integer categoryID, Model model) {
        model.addAttribute("id", categoryID);
        bindCategory(categoryID, model);
        return "category/category-detail";

    }

    @GetMapping("/editCategory/{categoryId}")
    public String showEditCategoryForm(@PathVariable Integer categoryId, Model model) {
        model.addAttribute("action", "/editedCategory/" + categoryId);
        bindCategory(categoryId, model);

        return "category/edit-category";
    }

    @PostMapping("/editedCategory/{categoryId}")
    public String saveEditedCategory(@PathVariable Integer categoryId, ProductCategory categoryForm) {
        service.updateCategory(categoryForm, categoryId);
        return "redirect:/categories";
    }

    @GetMapping("/removeCategory/{categoryID}")
    public String removeProductCategory(@PathVariable Integer categoryID) {
        service.deleteCategory(categoryID);
        return "redirect:/categories";
    }

    private void bindCategory(@RequestParam Integer categoryId, Model model) {
        var optionalCategory = service.findProductCategory(categoryId);
        if (optionalCategory.isPresent()) {
            var category = optionalCategory.get();
            model.addAttribute("category", category);
            model.addAttribute("title", "category");

        }
    }
}