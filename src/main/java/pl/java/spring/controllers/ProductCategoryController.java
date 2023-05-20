package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.java.spring.models.ProductCategory;
import pl.java.spring.services.ProductCategoryService;

// controller - zleca polecenia z serwisów
// tylko dostawanie request , zlecanie poleceń
@Controller
public class ProductCategoryController {
    // tworzę nowe pole dla klasy w celu posiadania pola
//    ProductCategoryService service = new ProductCategoryService();// tak się nie robi bo mamy zależność to my ja zarządzamy
    ProductCategoryService productCategoryService;

    // tu jest DI - dependency injection
    ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/Category")
    public String showCategory(Model model) {
        model.addAttribute("title", "Category");
        model.addAttribute("data", productCategoryService.getProductCategories());
        model.addAttribute("actionUri", "/saveCategory");// w tym bo to jest ten w którym się znajduje to moje action

        return "categories/Category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(ProductCategory productCategory) {
//        sprawdzam czy dana kategoria już istnieje
        productCategoryService.insertProductCategory(productCategory);
        return "redirect:/Category";
    }

    @GetMapping("/removeCategory/{categoryId}")
    public String removeCategory(@PathVariable Integer categoryId) {
        productCategoryService.removeProductCategory(categoryId);
        return "redirect:/Category";
    }

    @GetMapping("/editCategory/{categoryId}")
    public String showEditCategory(@PathVariable Integer categoryId, Model model) {
        model.addAttribute("actionUri", "/editedCategory/" + categoryId);
        bindCategoryModel(categoryId, model);
        return "categories/edit-category";

    }

    @PostMapping("/editedCategory/{categoryId}")
    public String saveEditedCategory(ProductCategory categoryForm, @PathVariable Integer categoryId) {
        productCategoryService.updateProductCategory(categoryForm, categoryId);
        return "redirect:/Category";
    }

    @GetMapping("/categoryDetails/{categoryId}")
    public String showDetails(@PathVariable Integer categoryId, Model model) {
        model.addAttribute("id", categoryId);
        bindCategoryModel(categoryId, model);
        return "categories/Cat";
    }


    private void bindCategoryModel(Integer productId, Model model) {
        var optionalCategory = productCategoryService.findProductCategory(productId);
        if (optionalCategory.isPresent()) {
            var category = optionalCategory.get();// to jestem pewna że lista nie jest pusta
            model.addAttribute("category", category);
        }
        //warunek jak jest pusty
        //todo

    }
}
