package com.example.eemart.serviceImpl;

import com.example.eemart.model.User;
import com.example.eemart.pojos.SignUpResponseDto;
import com.example.eemart.pojos.SignupDto;
import com.example.eemart.repository.UserRepository;
import com.example.eemart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public SignUpResponseDto signUp(SignupDto signupDto) throws NoSuchAlgorithmException {
        if (userRepository.existsByEmail(signupDto.getEmail()))
            return new SignUpResponseDto(HttpStatus.CONFLICT.getReasonPhrase(), "User Already exit");

        User newUser = User.builder()
                .firstName(signupDto.getFirstName())
                .lastName(signupDto.getLastName())
                .email(signupDto.getEmail())
                .password(hashPassword(signupDto.getPassword())).build();
        userRepository.save(newUser);
        return new SignUpResponseDto(HttpStatus.CREATED.getReasonPhrase(), "User created successfully");
    }
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }
}
