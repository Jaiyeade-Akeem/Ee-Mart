package com.example.eemart.service;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.Category;
import com.example.eemart.pojos.CategoryRequest;

public interface CategoryService {
    ApiResponse<?> createCategory(CategoryRequest category);
    String readCategory(String categoryName);
    ApiResponse<?> listAllCategories();
    ApiResponse<?> editCategory(Integer categoryId, CategoryRequest categoryRequest);
}
