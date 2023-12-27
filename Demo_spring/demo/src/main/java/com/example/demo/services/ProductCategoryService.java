package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.example.demo.models.ProductCategory;
@Service
public class ProductCategoryService { //cała logika biznesowa  w nim znajdowane są 
      List<ProductCategory> productCategoryList = new ArrayList<>();

    public ProductCategoryService() {
        productCategoryList.add(new ProductCategory(1, "phones", "", ""));
        productCategoryList.add(new ProductCategory(2, "tablets", "", ""));
        productCategoryList.add(new ProductCategory(3, "Tv", "", ""));

    }
    public List<ProductCategory> getDatabase(){
        return productCategoryList;
    }
    public void categoryExist(ProductCategory productCategoryForm){
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
    }
    public void insertCategory(Integer categoryId, ProductCategory categoryForm){
        productCategoryList = productCategoryList.stream()
            .map(productCategory -> {
                if (productCategory.getId().equals(categoryId)) {
                    categoryForm.setId(categoryId);
                    return categoryForm;
                }
                return productCategory;
            })
            .collect(Collectors.toList()); // utworzenie listy modyfikowalnej
        // .toList() // utworzona zostanie lista niemutowalna (niemodyfikowalnej)
    }
    public void deleteCategory( Integer categoryID){
        productCategoryList.removeIf(category -> category.getId().equals(categoryID));
    }


    public Optional<ProductCategory> findProductCategory(Integer categoryId) {
        return productCategoryList.stream()
            .filter(productCategory -> productCategory.getId().equals(categoryId))
            .findFirst();
    }

}

