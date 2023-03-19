package com.example.eemart.controller;

import com.example.eemart.pojos.SignUpResponseDto;
import com.example.eemart.pojos.SignupDto;
import com.example.eemart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public SignUpResponseDto signup(@RequestBody SignupDto signupDto) throws NoSuchAlgorithmException {
        return userService.signUp(signupDto);
    }
}
