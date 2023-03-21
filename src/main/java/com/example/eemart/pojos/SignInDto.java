package com.example.eemart.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class SignInDto {
    private String email;
    private String password;
}
