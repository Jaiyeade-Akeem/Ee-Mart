package com.example.eemart.pojos;


import lombok.*;

import javax.validation.constraints.NotNull;


@Data @AllArgsConstructor @Builder
public class CartDto {
    private @NotNull Integer productId;
    private @NotNull Integer quantity;

}
