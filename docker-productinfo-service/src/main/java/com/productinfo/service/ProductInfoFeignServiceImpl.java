package com.productinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productinfo.feignclient.IProductInfoFeignClient;
import com.productinfo.model.Product;

@Service
public class ProductInfoFeignServiceImpl implements IProductInfoService{

	@Autowired
	private IProductInfoFeignClient infoFeign;
	
	@Override
	public Product getById(int productId)  {
		return infoFeign.getById(productId);
	}

	@Override
	public List<Product> getAll() {
		return  infoFeign.getAll();
	}

	@Override
	public List<Product> getByBrand(String mbrand)  {
		return  infoFeign.getByBrand(mbrand);
	}

	@Override
	public List<Product> getByCategory(String mcategory)  {
		return  infoFeign.getByCategory(mcategory);
	}

}
