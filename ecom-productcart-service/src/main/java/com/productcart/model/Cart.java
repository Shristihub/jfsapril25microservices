package com.productcart.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cart {
	@Id
	@GeneratedValue(generator = "cart_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cart_gen",sequenceName = "cart_seq")
	private Integer cartId;
	private int userId;
    private double totalPrice;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
//	@JoinColumn(name = "cart_id")
	List<CartItem> cartItems;
	
	
	public void  calculateTotalPrice() {
		this.totalPrice =  cartItems.stream()
//				 				.mapToDouble(cartItem->cartItem.getTotalPrice()).sum();
								.mapToDouble(CartItem::getTotalPrice).sum();
	}

}
