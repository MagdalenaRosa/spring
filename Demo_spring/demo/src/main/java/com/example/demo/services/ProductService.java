package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {

    // product repository

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public boolean existsProductByName(Product productFrom) {
        return productRepository.existsByName(productFrom.getName());
    }

    public void insertProduct(Product productform) {
        var productExists = existsProductByName(productform);
        if (!productExists) {
            productform.setId(null);
            productRepository.save(productform); // sql: insert jeżeli obiekt NIE posiada id
        }

    }

    public void updateProduct(Product productFrom, Integer productId) {
        productFrom.setId(productId);
        productRepository.save(productFrom);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);

    }

    public Optional<Product> findProduct(Integer productId) {
        return productRepository.findById(productId);

    }

}
