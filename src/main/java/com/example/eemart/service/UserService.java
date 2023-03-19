package com.example.eemart.service;

import com.example.eemart.pojos.SignUpResponseDto;
import com.example.eemart.pojos.SignupDto;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    SignUpResponseDto signUp (SignupDto signupDto) throws NoSuchAlgorithmException;
}
