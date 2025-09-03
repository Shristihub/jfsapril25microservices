package com.productcatalog.service;

import com.productcatalog.model.CategoryDto;

public interface ICategoryService {
	
	//CRUD operation
	void addCategory(CategoryDto categoryDto);
	void updateCategory(CategoryDto categoryDto);
	void deleteCategory(int categoryId);
	CategoryDto getById(int categoryId) ;
	
}









