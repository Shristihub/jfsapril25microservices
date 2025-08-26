package com.productinfo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productinfo.model.Product;
import com.productinfo.service.IProductInfoService;

@RestController
@RequestMapping("/info-service/v1")
public class ProductInfoController {
	
	@Autowired
	private IProductInfoService productInfoService;

	//http://localhost:8082/info-service/v1/product-info/productid/1
	@GetMapping("/product-info/productid/{productId}")
	ResponseEntity<Product> getById(@PathVariable int productId) {
		Product product = productInfoService.getById(productId);
		return ResponseEntity.ok(product);
	}
	
	//http://localhost:8082/info-service/v1/product-info
	@GetMapping("/product-info")
	ResponseEntity<List<Product>> getAll(){
		List<Product> products = productInfoService.getAll();
		return ResponseEntity.ok(products);
	}
	
	//http://localhost:8082/info-service/v1/product-info/brand/Samsung
	@GetMapping("/product-info/brand/{brand}")
	ResponseEntity<List<Product>> getByBrand(@PathVariable String brand){
		List<Product> products = productInfoService.getByBrand(brand);
		return ResponseEntity.ok(products);
	}
	
	//http://localhost:8082/info-service/v1/product-info/category/electronics
	@GetMapping("/product-info/category/{category}")
	ResponseEntity<List<Product>> getByCategory(@PathVariable String category){
		List<Product> products = productInfoService.getByCategory(category);
		return ResponseEntity.ok(products);
	}
	
}
