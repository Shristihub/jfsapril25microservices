package com.productinfo.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.productinfo.model.Product;

// this is the client for catalog service
//pass the name of the service you want to call
@FeignClient(name="product-catalog")
public interface IProductInfoFeignClient {
	
	// annotate the method to map the url of productController
	
	@GetMapping("/catalog-service/v1/products/productId/{productId}")
	Product getById(@PathVariable int productId);
	
	@GetMapping("/catalog-service/v1/products")
	List<Product> getAll();
	
	@GetMapping("/catalog-service/v1/products/brand/{brand}")
	List<Product> getByBrand(@PathVariable String brand);
	
	@GetMapping("/catalog-service/v1/products/category")
	List<Product> getByCategory(@RequestParam String category);

}
