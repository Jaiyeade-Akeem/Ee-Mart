package com.example.eemart.serviceImpl;

import com.example.eemart.model.AuthenticationToken;
import com.example.eemart.model.User;
import com.example.eemart.repository.TokenRepository;
import com.example.eemart.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        if (tokenRepository.findByToken(authenticationToken.getToken()) == null)
            tokenRepository.save(authenticationToken);
    }

    @Override
    public AuthenticationToken getTokenByUser(User user) {
        AuthenticationToken existingToken = tokenRepository.findByUser(user);
        if (existingToken != null)
            return existingToken;
        return null;

    }

    @Override
    public User getUserByToken(String token) {
        User tokenOwner = tokenRepository.findByToken(token).getUser();
        if(tokenOwner != null)
            return tokenOwner;
        return null;
    }

    @Override
    public ResponseEntity<?> authenticateToken(String token) {
        if (tokenRepository.findByToken(token) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(token);

    }
}
