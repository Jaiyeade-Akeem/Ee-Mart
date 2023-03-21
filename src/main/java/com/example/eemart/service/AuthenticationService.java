package com.example.eemart.service;

import com.example.eemart.model.AuthenticationToken;
import com.example.eemart.model.User;
import com.example.eemart.pojos.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);
    AuthenticationToken getTokenByUser(User user);
    User getUserByToken(String token);
    ResponseEntity<?> authenticateToken(String token);
}
