package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductCategoryController {
    List<ProductCategory> data = new ArrayList<>();

    ProductCategoryController() {
        data.add(new ProductCategory(1, "Phone", "opis", "img"));
        data.add(new ProductCategory(2, "Tv", "opis", "img"));
    }

    @GetMapping("/Category")
    public String showCategory(Model model) {
        model.addAttribute("title", "Category");
        model.addAttribute("data", data);
        model.addAttribute("actionUri", "/saveCategory");// w tym bo to jest ten w którym się znajduje to moje action

        return "categories/Category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(ProductCategory productCategory) {
//        sprawdzam czy dana kategoria już istnieje
        var categoryExists = data.stream()
                .anyMatch(s -> s.getName().equals(productCategory.getName()));
        if (!categoryExists) {
            var nextId = 1;
            if (data.size() > 0) {
                var lastId = data.size() - 1;
                nextId = data.get(lastId).getId() + 1;
            }
            var category = new ProductCategory(nextId, productCategory.getName(), productCategory.getDesc(), productCategory.getImgUri());
            data.add(category);
        }
        return "redirect:/Category";
    }

    @GetMapping("/removeCategory")
    public String removeCategory(@RequestParam Integer categoryId) {
        data.removeIf(dataProduct -> dataProduct.getId().equals(categoryId));
        return "redirect:/Category";
    }

    @GetMapping("/editCategory")
    public String showEditCategory(@RequestParam Integer categoryId, Model model) {
        model.addAttribute("actionUri", "/editedCategory?categoryId=" + categoryId);
        bindCategoryModel(categoryId, model);
        return "categories/edit-category";

    }

    @PostMapping("/editedCategory")
    public String saveEditedCategory(ProductCategory categoryForm, @RequestParam Integer categoryId) {
        data = data.stream()
                .map(category -> {
                    if (category.getId().equals(categoryId)) {
                        categoryForm.setId(categoryId);
                        return categoryForm;
                    }
                    return category;

                })
                .collect(Collectors.toList());
        return "redirect:/Category";
    }

    @GetMapping("/categoryDetails")
    public String showDetails(@RequestParam Integer categoryId, Model model) {
        model.addAttribute("id", categoryId);
        bindCategoryModel(categoryId, model);
        return "categories/Cat";
    }

    private void bindCategoryModel(Integer productId, Model model) {
        var optionalCategory = data.stream()
                .filter(dbcategory -> dbcategory.getId().equals(productId))
                .findFirst();
        if (optionalCategory.isPresent()) {
            var category = optionalCategory.get();// to jestem pewna że lista nie jest pusta
            model.addAttribute("category", category);
        }
    }
}
