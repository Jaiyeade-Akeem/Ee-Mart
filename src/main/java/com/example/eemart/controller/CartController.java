package com.example.eemart.controller;

import com.example.eemart.model.Cart;
import com.example.eemart.pojos.CartDto;
import com.example.eemart.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartDto cartDto, @RequestParam String token){
        return cartService.addToCart(cartDto, token);
    }
    @GetMapping("/")
    public ResponseEntity<?> getCartItems(@RequestParam String token) {
        return new ResponseEntity<>(cartService.getCartItems(token), HttpStatus.OK);

    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeCart(@PathVariable Integer id, @RequestParam String token){
        return cartService.deleteCartItem(id, token);
    }
}
