package com.example.eemart.controller;

import com.example.eemart.config.ApiResponse;
import com.example.eemart.pojos.CheckoutItemDto;
import com.example.eemart.pojos.StripeResponse;
import com.example.eemart.service.OrderService;
import com.example.eemart.serviceImpl.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    StripeService stripeService;


    // stripe create session API
    @PostMapping("/create-checkout-session")
    public StripeResponse checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        return orderService.createSession(checkoutItemDtoList);
    }
    @PostMapping("/create-session")
    public StripeResponse checkoutList(@RequestParam String token) throws StripeException {
        return stripeService.createSession(token);
    }
    @PostMapping("/add")
    public ApiResponse<?> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId){
        return stripeService.placeOrder(token, sessionId);
    }


}
