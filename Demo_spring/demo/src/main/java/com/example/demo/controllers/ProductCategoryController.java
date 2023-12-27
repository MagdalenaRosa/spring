package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.ProductCategory;
import com.example.demo.services.ProductCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProductCategoryController {
 
    ProductCategoryService service;
 ProductCategoryController(ProductCategoryService service){
    this.service=service;
 }
  
    @GetMapping("/categories")
    public String showProductCategory(Model model) {
        model.addAttribute("title", "Category List");
        model.addAttribute("categoryDB",service.getDatabase());

        return "category/categories";
    }

    @PostMapping("/saveCategory")
    public String saveProductCategory(ProductCategory productCategoryForm) {
        service.categoryExist(productCategoryForm);
        return "redirect:/categories";
    }


    @GetMapping("/editCategory")
    public String showEditCategoryForm(@RequestParam Integer categoryId, Model model) {
        model.addAttribute("action", "/editedCategory?categoryId=" + categoryId);
        bindCategory(categoryId, model);

        return "category/edit-category"; 
    }

    @PostMapping("/editedCategory")
    public String saveEditedCategory(@RequestParam Integer categoryId, ProductCategory categoryForm) {
        service.insertCategory(categoryId, categoryForm);
        return "redirect:/categories";
    }


    @GetMapping("/removeCategory")
    public String removeProductCategory(@RequestParam Integer categoryID) {
       service.deleteCategory(categoryID);
        return "redirect:categories";
    }

    @GetMapping("/categoryDetail")
    public String showCategoryDetail(@RequestParam Integer categoryID, Model model) {
       
        bindCategory(categoryID, model);
        return "category/category-detail";

    }


private void bindCategory(@RequestParam Integer categoryId, Model model){
    var optionalCategory= service.findProductCategory(categoryId);
    if (optionalCategory.isPresent()) {
            var category = optionalCategory.get();
            model.addAttribute("category", category);

             model.addAttribute("title", "category");

    
    }}}