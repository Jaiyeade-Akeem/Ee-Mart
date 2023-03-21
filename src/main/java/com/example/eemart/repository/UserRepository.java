package com.example.eemart.repository;

import com.example.eemart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmail(String userEmail);
    User getUserById(Integer userId);
    Boolean existsByEmail(String userEmail);
}