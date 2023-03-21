package com.example.eemart.serviceImpl;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.model.Product;
import com.example.eemart.model.User;
import com.example.eemart.model.WishList;
import com.example.eemart.repository.ProductRepository;
import com.example.eemart.repository.WishListRepository;
import com.example.eemart.service.AuthenticationService;
import com.example.eemart.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    ProductRepository productRepository;
    @Override
    public ApiResponse<?> createWishList(Integer productId, String token) {
        authenticationService.authenticateToken(token);
        User user = authenticationService.getUserByToken(token);

        Product product = productRepository.getProductById(productId);

        WishList wishList = new WishList(user, product);
        wishListRepository.save(wishList);
        return new ApiResponse<>(HttpStatus.CREATED,true, "Wish list created", wishList);
    }

    @Override
    public List<WishList> readWishList(String token) {
        authenticationService.authenticateToken(token);
        User user = authenticationService.getUserByToken(token);
        return wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
