package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.ProductCategory;
import pl.java.spring.services.ProductCategoryService;

// controller - zleca polecenia z serwisów
// tylko dostawanie request , zlecanie poleceń
@Controller
public class ProductCategoryController {
    // tworzę nowe pole dla klasy w celu posiadania pola
//    ProductCategoryService service = new ProductCategoryService();// tak się nie robi bo mamy zależność to my ja zarządzamy
    ProductCategoryService service;

    // tu jest DI - dependency injection
    ProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }

    @GetMapping("/Category")
    public String showCategory(Model model) {
        model.addAttribute("title", "Category");
        model.addAttribute("data", service.getProductCategories());
        model.addAttribute("actionUri", "/saveCategory");// w tym bo to jest ten w którym się znajduje to moje action

        return "categories/Category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(ProductCategory productCategory) {
//        sprawdzam czy dana kategoria już istnieje
        service.insertProductCategory(productCategory);
        return "redirect:/Category";
    }

    @GetMapping("/removeCategory")
    public String removeCategory(@RequestParam Integer categoryId) {
        service.removeProductCategory(categoryId);
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
        service.updateProductCategory(categoryForm, categoryId);
        return "redirect:/Category";
    }

    @GetMapping("/categoryDetails")
    public String showDetails(@RequestParam Integer categoryId, Model model) {
        model.addAttribute("id", categoryId);
        bindCategoryModel(categoryId, model);
        return "categories/Cat";
    }


    private void bindCategoryModel(Integer productId, Model model) {
        var optionalCategory = service.findProductCategory(productId);
        if (optionalCategory.isPresent()) {
            var category = optionalCategory.get();// to jestem pewna że lista nie jest pusta
            model.addAttribute("category", category);
        }
    }
}
