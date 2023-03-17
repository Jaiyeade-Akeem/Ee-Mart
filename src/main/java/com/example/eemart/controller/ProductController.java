package com.example.eemart.controller;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.pojos.ProductDto;
import com.example.eemart.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    public ApiResponse<?> addProduct(@Valid @RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
    @GetMapping("/")
    public ApiResponse<?> listAllProducts(){
        return productService.listAllProducts();
    }
    @PutMapping("/update/{productID}")
    public ApiResponse<?> editProduct(@PathVariable Integer productID, @RequestBody ProductDto productDto){
        return productService.editProduct(productID, productDto);
    }
}
