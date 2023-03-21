package com.example.eemart.serviceImpl;

import com.example.eemart.model.Cart;
import com.example.eemart.model.User;
import com.example.eemart.pojos.CartDto;
import com.example.eemart.pojos.CartItemDto;
import com.example.eemart.pojos.CartItemsResponseDto;
import com.example.eemart.repository.CartRepository;
import com.example.eemart.repository.ProductRepository;
import com.example.eemart.repository.UserRepository;
import com.example.eemart.service.AuthenticationService;
import com.example.eemart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Cart addToCart(CartDto cartDto, String token) {
        User authentiUser = authenticationService.getUserByToken(token);
        if (authentiUser == null)
            return (Cart) ResponseEntity.badRequest();
        Cart newCart = Cart.builder()
                .user(userRepository.getUserById(authentiUser.getId()))
                .product(productRepository.getProductById(cartDto.getProductId()))
                .quantity(cartDto.getQuantity()).build();
        return cartRepository.save(newCart);
    }

    @Override
    public CartItemsResponseDto getCartItems(String token) {
        User authentiUser = authenticationService.getUserByToken(token);
        if (authentiUser == null)
            return (CartItemsResponseDto) ResponseEntity.badRequest();
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(authentiUser);
        List<CartItemDto> cartDto = new ArrayList<>();
        double totalCost = 0.0;
        for (Cart cart : cartList)
            cartDto.add(CartItemDto.builder().product(cart.getProduct()).quantity(cart.getQuantity()).build());
        for (CartItemDto cart : cartDto)
            totalCost += (cart.getQuantity() * cart.getProduct().getPrice());
        return new CartItemsResponseDto(cartDto, totalCost);

    }

    @Override
    public ResponseEntity<?> deleteCartItem(Integer cartItemId, String token) {
        User authentiUser = authenticationService.getUserByToken(token);
        if (authentiUser == null)
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        boolean isCartToBeDeletedExist = cartRepository.existsById(cartItemId);
        if (!isCartToBeDeletedExist)
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        Cart cartOwner = cartRepository.getCartById(cartItemId);
        if (authentiUser != cartOwner.getUser())
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        cartRepository.deleteById(cartItemId);
        return new ResponseEntity<>("Cart deleted successfully", HttpStatus.OK);
    }
}
