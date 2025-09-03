package com.productcart.service;

import com.productcart.model.CartDto;
import com.productcart.model.Type;

public interface ICartService {
	
	Type addToCart(int userId,  int productId,  int quantity);
	CartDto updateCart(int userId,  int productId,  int quantity);
	void removeFromCart(int userId,int productId);
	CartDto viewCart(int userId);
	void clearCart(int userId);
}
