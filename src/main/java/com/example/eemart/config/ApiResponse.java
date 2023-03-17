package com.example.eemart.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ApiResponse <T>{
    private HttpStatus status;
    private boolean success;
    private String message;
    private T body;
    public String getTime() {
        return LocalDateTime.now().toString();
    }
}
