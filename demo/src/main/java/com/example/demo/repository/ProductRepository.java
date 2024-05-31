package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
    List<Product> findBySoldFalse(Sort sort);
    List<Product> findBySoldFalseAndPriceLessThan(double maxPrice, Sort sort);
}
