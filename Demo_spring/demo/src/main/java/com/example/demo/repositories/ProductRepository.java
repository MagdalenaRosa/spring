package com.example.demo.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Product;

// integer bo po id 
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // automatyczni się robi sql
    boolean existsByName(String name);
    // patrzy czy jest już coś takiego jak name)

    Optional<Product> findByName(String name);

    // select * from Product where BETWEEN from AND to;
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    // select * from Product where name = ? OR desc = ?
    List<Product> findAllByNameOrDesc(String name, String desc);

    // select * from Product where name = ? AND desc = ?
    List<Product> findAllByNameAndDesc(String name, String desc);

    // select * from Product where name like ?
    List<Product> findAllByNameLike(String name);

}
