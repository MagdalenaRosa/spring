package pl.java.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private List<ProductCategory> data = new ArrayList<>();

    public ProductCategoryService() {
        data.add(new ProductCategory(1, "Phone", "opis", "img"));
        data.add(new ProductCategory(2, "Tv", "opis", "img"));
    }

    public List<ProductCategory> getProductCategories() {
        return data;
    }

    public Optional<ProductCategory> findProductCategory(Integer categoryId) {
        return data.stream()
                .filter(dbcategory -> dbcategory.getId().equals(categoryId))
                .findFirst();

    }

    public void removeProductCategory(Integer categoryId) {
        data.removeIf(dataProduct -> dataProduct.getId().equals(categoryId));

    }

    public void insertProductCategory(ProductCategory productCategory) {
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
    }

    public void updateProductCategory(ProductCategory categoryForm, @RequestParam Integer categoryId) {
        data = data.stream()
                .map(category -> {
                    if (category.getId().equals(categoryId)) {
                        categoryForm.setId(categoryId);
                        return categoryForm;
                    }
                    return category;

                })
                .collect(Collectors.toList());
    }
}
