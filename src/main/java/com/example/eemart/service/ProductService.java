package com.example.eemart.service;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.Category;
import com.example.eemart.pojos.CategoryRequest;
import com.example.eemart.pojos.ProductDto;

public interface ProductService {
    ApiResponse<?> addProduct(ProductDto productDto);
    ApiResponse<?> listAllProducts();
    ApiResponse<?> editProduct(Integer productId, ProductDto productDto);
}
