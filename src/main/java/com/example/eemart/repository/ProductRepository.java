package com.example.eemart.repository;

import com.example.eemart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product getProductByName(String productName);
    Product getProductById(Integer productId);
}