package com.example.eemart.service;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.User;
import com.example.eemart.model.WishList;

import java.util.List;

public interface WishListService {
    ApiResponse<?> createWishList(Integer productId, String token);
    List<WishList> readWishList(String token);
}
