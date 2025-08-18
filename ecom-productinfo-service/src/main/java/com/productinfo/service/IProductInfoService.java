package com.productinfo.service;

import java.util.List;

import com.productinfo.exceptions.ProductNotFoundException;
import com.productinfo.model.Product;


public interface IProductInfoService {
	
	Product getById(int productId) throws ProductNotFoundException;
	List<Product> getAll();
	
	List<Product> getByBrand(String brand)throws ProductNotFoundException;
	List<Product> getByCategory(String category)throws ProductNotFoundException;

}
