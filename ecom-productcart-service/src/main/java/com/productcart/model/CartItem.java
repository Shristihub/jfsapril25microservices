package com.productcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CartItem {
	@Id
	@GeneratedValue(generator = "cartitem_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "cartitem_gen",sequenceName = "cartitem_seq")
	private Integer cartItemId;
	private int productId;
	private String productName;
	private double price;
	private int quantity;
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	
	public double getTotalPrice() {
		return price*quantity;
	}
	
}
