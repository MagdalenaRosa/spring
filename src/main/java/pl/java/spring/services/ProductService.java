package pl.java.spring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.Product;
import pl.java.spring.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
