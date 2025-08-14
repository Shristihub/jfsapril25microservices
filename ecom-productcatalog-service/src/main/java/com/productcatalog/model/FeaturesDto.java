package com.productcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeaturesDto {
	private Integer featuresId;
	private String color;
	private String material;
	private String description;
	

}
