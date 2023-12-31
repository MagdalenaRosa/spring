package com.example.demo.controllers;

import com.example.demo.models.ProductCategory;
import com.example.demo.services.ProductCategoryService;

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
public class ProductCategoryController {

    final ProductCategoryService service;

    @GetMapping("/categories")
    public String showProductCategory(Model model) {
        model.addAttribute("title", "Category List");
        model.addAttribute("categoryDB", service.findAllCategories());
        model.addAttribute("action", "/saveCategory");

        return "category/categories";
    }

    @PostMapping("/saveCategory")
    public String saveProductCategory(@Valid ProductCategory productCategoryForm, BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("category", productCategoryForm);

        } else {
            service.insertCategory(productCategoryForm);

        }

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
    public String saveEditedCategory(@PathVariable Integer categoryId, @Valid ProductCategory categoryForm,
            BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("product", categoryForm);
            return "redirect:/editCategory/" + categoryId;
        } else {
            service.updateCategory(categoryForm, categoryId);
            return "redirect:/categories";
        }
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