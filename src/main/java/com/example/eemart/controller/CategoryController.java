package com.example.eemart.controller;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.pojos.CategoryRequest;
import com.example.eemart.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public ApiResponse<?> createCategory(@Valid @RequestBody CategoryRequest category) {
        return categoryService.createCategory(category);
    }
    @GetMapping("/")
    public ApiResponse<?> listCategories(){
        return categoryService.listAllCategories();
    }
    @PutMapping("/update/{categoryID}")
    public ApiResponse<?> editCategory(@Valid @PathVariable Integer categoryID, @RequestBody CategoryRequest categoryRequest){
        return categoryService.editCategory(categoryID, categoryRequest);
    }
}
