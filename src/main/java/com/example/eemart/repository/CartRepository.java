package com.example.eemart.repository;

import com.example.eemart.model.Cart;
import com.example.eemart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
//    String de(Integer id, User user);
    Cart getCartById(Integer id);
}