package com.productcart.feignclient;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.productcart.model.Product;

@FeignClient(name = "product-info")
public interface IProductInfoFeign {
	
	@GetMapping("/info-service/v1/product-info/productid/{productId}")
	Optional<Product> getById(@PathVariable int productId);

}
