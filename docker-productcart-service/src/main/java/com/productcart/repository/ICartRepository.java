package com.productcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productcart.model.Cart;

public interface ICartRepository extends JpaRepository<Cart, Integer>{

	Optional<Cart> findByUserId(int userId);
}
