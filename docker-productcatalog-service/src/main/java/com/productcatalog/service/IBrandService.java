package com.productcatalog.service;

import com.productcatalog.exceptions.BrandNotFoundException;
import com.productcatalog.model.BrandDto;

public interface IBrandService {
	
	//CRUD operation
	void addBrand(BrandDto brandDto);
	void updateBrand(BrandDto brandDto);
	void deleteBrand(int brandId);
	BrandDto getById(int brandId) throws BrandNotFoundException;
	

}









