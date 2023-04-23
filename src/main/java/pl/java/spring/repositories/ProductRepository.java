package pl.java.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.spring.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {//klasa, wartośc klucza id


    boolean existsByName(String name);
    // tu sb nadpisujemu metodę której nie ma w jparepository
}
