package com.example.eemart.service;

import com.example.eemart.pojos.CheckoutItemDto;
import com.example.eemart.pojos.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;


import java.util.List;

public interface OrderService {
    StripeResponse createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;
}
