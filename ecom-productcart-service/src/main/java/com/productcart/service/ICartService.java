package com.productcart.service;

import com.productcart.model.CartDto;

public interface ICartService {
	
	CartDto addToCart(int userId,  int productId,  int quantity);
	CartDto updateCart(int userId,  int productId,  int quantity);
	void removeFromCart(int userId,int productId);
	CartDto viewCart(int userId);
	void clearCart(int userId);
}
