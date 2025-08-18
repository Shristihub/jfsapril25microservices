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
public class Brand {
	private Integer brandId;
	private String brandName;
	
	@ToString.Exclude
	@JsonIgnore
	private List<Product> products;
	
	public Brand(String brandName) {
		super();
		this.brandName = brandName;
	}

}
