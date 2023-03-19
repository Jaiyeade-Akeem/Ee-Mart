package com.example.eemart.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @AllArgsConstructor @Getter @Setter
public class SignUpResponseDto {
        private String status;
        private String message;
}
