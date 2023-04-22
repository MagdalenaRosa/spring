package pl.java.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> database = new ArrayList<>();

    public ProductService() {
        database.add(new Product(1, "iPhone X", "fsdfsdfsdfsds", "https://allegro.stati.pl/AllegroIMG/PRODUCENCI/APPLE/iPhone%20X/c.jpg", BigDecimal.valueOf(10.99)));
        database.add(new Product(3, "iPhone 11", "dfsaqwrewrqewre", "https://files.refurbed.com/ii/iphone-11-1568185539.jpg?t=resize&h=600&w=800", BigDecimal.valueOf(15.10)));
        database.add(new Product(6, "iPhone 12", "dsfsdfsdfsd", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/9/pr_2022_9_23_9_7_32_971_05.jpg", BigDecimal.valueOf(20.33)));
        database.add(new Product(9, "iPhone 13", "sdfsdfsdfsdf", "https://www.apple.com/newsroom/images/product/iphone/standard/Apple-iPhone-14-iPhone-14-Plus-hero-220907_Full-Bleed-Image.jpg.large.jpg", BigDecimal.valueOf(17.77)));
    }

    public List<Product> getProduct() {
        return database;
    }

    public Optional<Product> findProduct(Integer productId) {
        return database.stream()
                .filter(dbproduct -> dbproduct.getId().equals(productId))
                .findFirst();

    }

    public void insertProduct(Product productFrom) {
        var productExists = database.stream()
                .map(Product::getName)
                .anyMatch(dbProductName -> productFrom.getName().equals(dbProductName));
        if (!productExists) {
            var nextId = 1;
            if (database.size() > 0) {
                var lastId = database.size() - 1;
                nextId = database.get(lastId).getId() + 1;
            }
            productFrom.setId(nextId);
            database.add(productFrom);
        }
    }

    public void removeProduct(@RequestParam Integer productId) {
        database.removeIf(dbProduct -> dbProduct.getId().equals(productId));

    }

    public void updateProduct(@RequestParam Integer productId, Product productForm) {
        database = database.stream()//nadpisuje starą liste nową listą
                .map(product -> {
                    if (product.getId().equals(productId)) {
                        productForm.setId(productId);// dodajemy ID bo nie ma go w formularzu
                        return productForm;//ze zmianą w formularzu
                    }
                    return product;// bez zmian
                })
                .collect(Collectors.toList());
    }

}
