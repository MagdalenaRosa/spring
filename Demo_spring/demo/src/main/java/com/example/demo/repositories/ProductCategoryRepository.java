package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    boolean existsByName(String name);
}
