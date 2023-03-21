package com.example.eemart.service;

import com.example.eemart.model.Cart;
import com.example.eemart.pojos.CartDto;
import com.example.eemart.pojos.CartItemsResponseDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    Cart addToCart (CartDto cartDto, String token);
    CartItemsResponseDto getCartItems(String token);
    ResponseEntity<?> deleteCartItem(Integer cartItemId, String token);
}
