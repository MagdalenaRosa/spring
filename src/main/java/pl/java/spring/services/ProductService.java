package pl.java.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.Product;
import pl.java.spring.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        productRepository.save(new Product("iPhone X", "fsdfsdfsdfsds", "https://allegro.stati.pl/AllegroIMG/PRODUCENCI/APPLE/iPhone%20X/c.jpg", BigDecimal.valueOf(10.99)));
        productRepository.save(new Product("iPhone 11", "dfsaqwrewrqewre", "https://files.refurbed.com/ii/iphone-11-1568185539.jpg?t=resize&h=600&w=800", BigDecimal.valueOf(15.10)));
        productRepository.save(new Product("iPhone 12", "dsfsdfsdfsd", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/9/pr_2022_9_23_9_7_32_971_05.jpg", BigDecimal.valueOf(20.33)));
        productRepository.save(new Product("iPhone 13", "sdfsdfsdfsdf", "https://www.apple.com/newsroom/images/product/iphone/standard/Apple-iPhone-14-iPhone-14-Plus-hero-220907_Full-Bleed-Image.jpg.large.jpg", BigDecimal.valueOf(17.77)));
    }

    public List<Product> getProduct() {
        return productRepository.findAll();//zwraca liste z bazy
    }

    public Optional<Product> findProduct(Integer productId) {
        return productRepository.findById(productId);
    }

    public void insertProduct(Product productFrom) {
        var productExists = existsProductByName(productFrom);
        if (!productExists) {
            productFrom.setId(null);
            productRepository.save(productFrom);//jak nie posiada id

        }
    }

    private boolean existsProductByName(Product productFrom) {
        return productRepository.existsByName(productFrom.getName());
    }

    public void removeProduct(@RequestParam Integer productId) {
        productRepository.deleteById(productId);

    }

    public void updateProduct(@RequestParam Integer productId, Product productForm) {//jak posiada id
        productForm.setId(productId);
        productRepository.save(productForm);
    }
}
