package com.example.eemart.controller;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.WishList;
import com.example.eemart.pojos.ProductDto;
import com.example.eemart.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@AllArgsConstructor
public class WishListController {
    @Autowired
    WishListService wishListService;
    @PostMapping("/add")
    public ApiResponse<?> addWishList(@RequestBody Integer productId, @RequestParam String token){
        return wishListService.createWishList(productId, token);
    }
    @GetMapping("/{token}")
    public List<WishList> getWishList(@PathVariable("token") String token){
        return wishListService.readWishList(token);
    }
}
