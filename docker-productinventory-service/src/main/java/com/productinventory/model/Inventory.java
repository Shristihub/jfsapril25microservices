package com.productinventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Inventory {
	@Id
	@GeneratedValue(generator = "inventory_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "inventory_gen",sequenceName = "inventory_seq")
	private Integer inventoryId;
	private int productId;
	private int stock;
	

	
}
