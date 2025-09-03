package com.productcart.service;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcart.exceptions.CartNotFoundException;
import com.productcart.exceptions.ProductNotFoundException;
import com.productcart.feignclient.IProductInfoFeign;
import com.productcart.model.Cart;
import com.productcart.model.CartDto;
import com.productcart.model.CartItem;
import com.productcart.model.Failure;
import com.productcart.model.Product;
import com.productcart.model.Type;
import com.productcart.repository.ICartRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class CartServiceImpl implements ICartService{
	
	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private IProductInfoFeign feign;
	
	@Autowired
	private ModelMapper mapper;
	@Override
//	@CircuitBreaker(name = "cart-cbservice",fallbackMethod = "fallbackAddToCart")
	@Retry(name = "cart-retryservice",fallbackMethod = "fallbackAddToCart")
	public Type addToCart(int userId, int productId, int quantity) {
		// check if a cart exists for the specific userid
		// if so get the cart
		Cart cart = getCartByUserId(userId);
		cart.setUserId(userId);
		//get the product from product-info microservice
		Product product = getByProductId(productId);

		// check if the product is available in the existing cart
		Cart newCart =  addCartItemtoCart(cart,quantity,product,productId);
		//calculate the totalprice of cart
		newCart.calculateTotalPrice();
		//save it to repository
		Cart savedCart =  cartRepository.save(cart);
		//convert to dto and send to client
		return mapper.map(savedCart, CartDto.class);
	}
	
	// fallback method
    public Type fallbackAddToCart(Exception e){
    	System.out.println(e.getMessage());
   	  return new Failure("Technical Error");
    }
	
	private  Cart getCartByUserId(int userId) {
		Cart cart = cartRepository.findByUserId(userId)
			      // else create a new cart
		          .orElseGet(()->{
		        	  Cart newCart =  new Cart();
		        	  newCart.setUserId(userId);
		        	  newCart.setCartItems(new ArrayList<>());
		        	  return newCart;
		          });
		return cart;
	}
	
	private Product getByProductId(int productId) {
		//get the product from product-info microservice
		Product product = feign.getById(productId)
		//else throw exception
		.orElseThrow(()->new ProductNotFoundException("invalid id"));
		return product;
	}
     private Cart addCartItemtoCart(Cart cart,int quantity, Product product,int productId) {
    	 Optional<CartItem> existingItemOpt =  cart.getCartItems().stream()
 		        .filter(cartItem->cartItem.getProductId()== productId)
 		        .findFirst();
 		// if available in the cart, 
 		if(existingItemOpt.isPresent()) {
 			//then get the product
 			CartItem existingItem = existingItemOpt.get();
 			//add the quantity to the existing qunatity
 			existingItem.setQuantity(quantity+existingItem.getQuantity());
 		}else {
 		//if not in cart, create a new cartItem
 		 CartItem newItem =  new CartItem();
 		 //set the values;
 		 newItem.setProductId(productId);
 		 newItem.setProductName(product.getProductName());
 		 newItem.setPrice(product.getPrice());
 		 newItem.setQuantity(quantity);
 		 //set the cart
 		 newItem.setCart(cart);
 		 // get the old cartItems and add the newItem
 		 cart.getCartItems().add(newItem);		
 		}
 		return cart;
     }
	 
	
	@Override
	public CartDto updateCart(int userId, int productId, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFromCart(int userId, int productId) {
		Cart cart = cartRepository.findByUserId(userId)
				.orElseThrow(()-> new CartNotFoundException("invalid userId"));
		boolean removed = cart.getCartItems()
				            .removeIf(cartItem->cartItem.getProductId()== productId);
		//recalculate the price
		if(removed) {
			cart.calculateTotalPrice();
		}
	}

	@Override
	public CartDto viewCart(int userId) {
		Cart cart = cartRepository.findByUserId(userId)
				.orElseThrow(()-> new CartNotFoundException("invalid userId"));
		return mapper.map(cart, CartDto.class);
	}

	@Override
	public void clearCart(int userId) {
		Cart cart = cartRepository.findByUserId(userId)
				.orElseThrow(()-> new CartNotFoundException("invalid userId"));
//		cartRepository.delete(cart); //cart will be removed completely
		
		// just remove/clear the items in the cart instead of removing the cart
		cart.getCartItems().clear();
		
		
	}
	

}
