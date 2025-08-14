package com.productcatalog.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {
	@Column(length=20)
	private String productName;
	@Id
	@GeneratedValue(generator = "product_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "product_gen",sequenceName = "product_seq")
	private Integer productId;
	@Column(name="cost")
	private double price;
	//save the child entity before the parent entity
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "features_id")
	private Features features;
	
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="product_id") // foreign key in offers table
	private List<Offers> offers;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name="product_category",
	        joinColumns = @JoinColumn(name="product_id"),
	        inverseJoinColumns = @JoinColumn(name="category_id"))
	private List<Category> categories;
	
	@ElementCollection
	@CollectionTable(name = "payment",joinColumns = @JoinColumn(name="product_id"))
	private List<String> paymentModes; //COD,UPI,CREDITCARD,DEBITCARD
	
	@ElementCollection
	@CollectionTable(name = "delivery",joinColumns = @JoinColumn(name="product_id"))
	private List<String> deliveryTypes; //standard, prime,free
	
	
}








