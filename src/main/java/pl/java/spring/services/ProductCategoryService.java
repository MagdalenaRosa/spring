package pl.java.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.ProductCategory;
import pl.java.spring.repositories.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

// TU SĄ TE KTÓRE mają jakieś działania
// one sa pod obsługe danych -> zarządca danych
// zarządza wewnętrzną bazą
// każdy kto uzyje tego serwisu każdy może miećskutek w innych bez konieczności pisania na nowo
// nowe zmiany nie ingeruja w inne pliku-> nie da się zepsuć


@Service // to nie my zarządzamy tylko serwis tu jest IoC- inversion of control
// mówi się na to zawzwyczaj bean ⾖ (component - spring)-> chodzi o adnotacje
// beanem może być:
//- @controller
//- @service
public class ProductCategoryService {
    ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
        productCategoryRepository.save(new ProductCategory("Phone", "opis", "img"));
        productCategoryRepository.save(new ProductCategory("Tv", "opis", "img"));
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> findProductCategory(Integer categoryId) {
        return productCategoryRepository.findById(categoryId);

    }

    public void removeProductCategory(Integer categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }

    public void insertProductCategory(ProductCategory productCategory) {
        var categoryExists = existsProductCategoryByName(productCategory);
        if (!categoryExists) {
            productCategory.setId(null);
            productCategoryRepository.save(productCategory);
        }
    }

    private boolean existsProductCategoryByName(ProductCategory productCategory) {
        return productCategoryRepository.existsByName(productCategory.getName());

    }

    public void updateProductCategory(ProductCategory categoryForm, @RequestParam Integer categoryId) {
        categoryForm.setId(categoryId);
        productCategoryRepository.save(categoryForm);
    }
}
