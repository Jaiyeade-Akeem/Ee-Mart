package com.example.eemart.serviceImpl;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.Category;
import com.example.eemart.pojos.CategoryRequest;
import com.example.eemart.repository.CategoryRepository;
import com.example.eemart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ApiResponse<?> createCategory(CategoryRequest category) {
        if (readCategory(category.getCategoryName()) != null)
            return new ApiResponse<>(false, "category already created",null);

        Category newCategory = Category.builder()
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .build();
        categoryRepository.save(newCategory);
        return new ApiResponse<>(true, "user successfully created", newCategory);
    }

    @Override
    public String readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public ApiResponse<?> listAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty())
            return new ApiResponse<>(false, "No record found", null);
        return new ApiResponse<>(true, categoryList.size() + " record/s found", categoryList);

    }

    @Override
    public ApiResponse<?> editCategory(Integer categoryId, CategoryRequest categoryRequest) {
        Optional<Category> editedCategory = categoryRepository.findById(categoryId);
        if (editedCategory.isEmpty())
            return new ApiResponse<>(false, "Category with the give Id not found", null);

        editedCategory.get().setCategoryName(categoryRequest.getCategoryName());
        editedCategory.get().setDescription(categoryRequest.getDescription());
        editedCategory.get().setImageUrl(categoryRequest.getImageUrl());
        categoryRepository.save(editedCategory.get());
        return new ApiResponse<>(true, "Category updated successfully", editedCategory);
    }
}
