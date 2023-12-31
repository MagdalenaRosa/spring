package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.models.ProductCategory;
import com.example.demo.repositories.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductCategoryService { // cała logika biznesowa w nim znajdowane są

    final ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAllCategories() {
        return productCategoryRepository.findAll();
    }

    public boolean categoryExistByName(ProductCategory productCategoryForm) {
        return productCategoryRepository.existsByName(productCategoryForm.getName());
    }

    public void insertCategory(ProductCategory categoryForm) {
        var productCategoryExist = categoryExistByName(categoryForm);
        if (!productCategoryExist) {
            categoryForm.setId(null);
            productCategoryRepository.save(categoryForm);
        }
    }

    public void deleteCategory(Integer categoryID) {
        productCategoryRepository.deleteById(categoryID);
    }

    public void updateCategory(ProductCategory categoryForm, Integer categoryId) {
        categoryForm.setId(categoryId);
        productCategoryRepository.save(categoryForm);
    }

    public Optional<ProductCategory> findProductCategory(Integer categoryId) {
        return productCategoryRepository.findById(categoryId);
    }
}
