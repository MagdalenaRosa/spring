package pl.java.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.spring.models.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    boolean existsByName(String name);

}
