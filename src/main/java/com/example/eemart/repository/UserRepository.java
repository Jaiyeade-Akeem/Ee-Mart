package com.example.eemart.repository;

import com.example.eemart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmail(String userEmail);
    Boolean existsByEmail(String userEmail);
}