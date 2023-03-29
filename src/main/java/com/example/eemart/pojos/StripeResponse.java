package com.example.eemart.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Getter
public class StripeResponse {
    private String sessionUrl;
    private String sessionId;
}
