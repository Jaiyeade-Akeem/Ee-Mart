package com.example.eemart.pojos;

import lombok.Data;

@Data
public class CheckoutItemDto {
    private String productName;
    private int  quantity;
    private double price;
    private long productId;
}
