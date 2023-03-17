package com.example.eemart.repository;

import com.example.eemart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    String findByCategoryName(String categoryName);

}