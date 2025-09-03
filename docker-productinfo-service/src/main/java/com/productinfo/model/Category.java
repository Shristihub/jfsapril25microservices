package com.productinfo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
	private Integer categoryId;
	private String categoryName;
	
	@ToString.Exclude
	@JsonIgnore
	private List<Product> products;
	

}
