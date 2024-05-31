package com.example.demo.Repository;

import com.example.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    List<Product> findAllByAvailableQuantityGreaterThan(int i);
}
