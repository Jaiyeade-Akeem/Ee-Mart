package com.example.eemart.serviceImpl;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.Category;
import com.example.eemart.model.Product;
import com.example.eemart.pojos.ProductDto;
import com.example.eemart.repository.CategoryRepository;
import com.example.eemart.repository.ProductRepository;
import com.example.eemart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ApiResponse<?> addProduct(ProductDto productDto) {
        if (getProductFromDto(productDto).getCategory() == null)
            return new ApiResponse<>(HttpStatus.NOT_FOUND, false,"Product category not found", null);
        if (productRepository.getProductByName(productDto.getName().toLowerCase()) != null)
            return new ApiResponse<>(HttpStatus.CONFLICT, false, "Product already created", null);

        Product dbProduct = productRepository.save(getProductFromDto(productDto));
        return new ApiResponse<>(HttpStatus.CREATED, true, "Product added successfully", dbProduct);
    }

    @Override
    public ApiResponse<?> listAllProducts() {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty())
            return new ApiResponse<>(HttpStatus.NOT_FOUND,false, "No record found", null);
        return new ApiResponse<>(HttpStatus.FOUND,true, productList.size() + " record/s found", productList);
    }

    @Override
    public ApiResponse<?> editProduct(Integer productId, ProductDto productDto) {
        Optional<Product> editedProduct = productRepository.findById(productId);
        if (editedProduct.isEmpty())
            return new ApiResponse<>(HttpStatus.NOT_FOUND,false, "Product with the given Id not found", null);

        editedProduct.get().setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
        editedProduct.get().setName(productDto.getName());
        editedProduct.get().setDescription(productDto.getDescription());
        editedProduct.get().setPrice(productDto.getPrice());
        editedProduct.get().setImageURL(productDto.getImageURL());

        productRepository.save(editedProduct.get());
        return new ApiResponse<>(HttpStatus.OK,true, "Category updated successfully", editedProduct);
    }

    public Product getProductFromDto(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imageURL(productDto.getImageURL())
                .category(categoryRepository.findById(productDto.getCategoryId()).get()).build();
    }
}
