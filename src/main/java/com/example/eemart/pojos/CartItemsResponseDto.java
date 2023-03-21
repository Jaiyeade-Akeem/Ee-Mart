package com.example.eemart.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class CartItemsResponseDto {
    private @NotNull List<CartItemDto> cartItems;
    private @NotNull Double totalCost;
}
