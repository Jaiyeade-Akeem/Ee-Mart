package com.example.eemart.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ApiResponse <T>{
    private boolean success;
    private String message;
    private T body;
//    @CreationTimestamp
//    private Time time;
//    public String getTime() {
//        return LocalDateTime.now().toString();
//    }
}
