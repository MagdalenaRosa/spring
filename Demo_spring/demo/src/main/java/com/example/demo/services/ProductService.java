package com.example.demo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.models.Product;

@Service
public class ProductService {
    List<Product> database = new ArrayList<>();

    public ProductService() {
        database.add(new Product(1, "Iphone 11", "", "", BigDecimal.valueOf(345.87)));
        database.add(new Product(3, "Iphone 12", "", "", BigDecimal.valueOf(345.87)));
        database.add(new Product(13, "Iphone 13", "", "", BigDecimal.valueOf(345.87)));
        database.add(new Product(14, "Iphone 15", "", "", BigDecimal.valueOf(345.87)));

    }
      public List<Product> getDatabase(){
        return database;
    }
    public void insertProduct(Product productform){
        var productExist = database.stream().anyMatch(product -> product.getName().equals(productform.getName()));
        if (!productExist) {
            var nextId = 1;
            if (!database.isEmpty()) {
                var lastIndex = database.size() - 1;
                nextId = database.get(lastIndex).getId() + 1;
            }

            var product = new Product(nextId, productform.getName(), productform.getDesc(), productform.getUrlUri(), productform.getPrice());
            database.add(product);
    }
    
    }
    public void editProduct(Product productFrom,Integer productId){
          database = database.stream()
            .map(product -> {
                if (product.getId().equals(productId)) {
                    productFrom.setId(productId);
                    return productFrom;
                }
                return product;
            })
            .collect(Collectors.toList());
    }
    public void deleteProduct( Integer productId){
        database.removeIf(dbProduct -> dbProduct.getId().equals(productId));

    }
    public Optional<Product> findProduct(Integer categoryId) {
        return database.stream()
            .filter(productCategory -> productCategory.getId().equals(categoryId))
            .findFirst();
    }

}
