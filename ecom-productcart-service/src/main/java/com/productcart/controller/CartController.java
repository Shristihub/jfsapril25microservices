package com.productcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcart.model.CartDto;
import com.productcart.service.ICartService;

@RestController
@RequestMapping("/cart-service/v1")
public class CartController {
	
	@Autowired
	private ICartService cartService;

	@PostMapping("/cart-info/add-to-cart")
	ResponseEntity<CartDto> addToCart(@RequestParam int userId, @RequestParam int productId, @RequestParam int quantity){
		CartDto cartDto =  cartService.addToCart(userId, productId, quantity);
		return ResponseEntity.ok(cartDto);
	}
	
	@PostMapping("/cart-info/update-cart")
	ResponseEntity<CartDto> updateCart(@RequestParam int userId, @RequestParam int productId, @RequestParam int quantity){
		CartDto cartDto =  cartService.updateCart(userId, productId, quantity);
		return ResponseEntity.ok(cartDto);
	}
	
	@GetMapping("/cart-info/remove-from-cart")
	ResponseEntity<Void> removeFromCart(@RequestParam int userId, @RequestParam int productId){
		cartService.removeFromCart(userId, productId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/cart-info/view-cart/userId/{userId}")
	ResponseEntity<CartDto> viewCart(@PathVariable int userId){
		CartDto cartDto =  cartService.viewCart(userId);
		return ResponseEntity.ok(cartDto);
	}
	
	@GetMapping("/cart-info/clear-cart/userId/{userId}")
	ResponseEntity<Void> clearCart(@PathVariable int userId){
		cartService.clearCart(userId);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
}
