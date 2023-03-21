package com.example.eemart.pojos;

import com.example.eemart.model.Product;
import lombok.*;

import javax.validation.constraints.NotNull;


@Data @AllArgsConstructor @Builder
public class CartItemDto {
    private @NotNull Product product;
    private @NotNull Integer quantity;

}
