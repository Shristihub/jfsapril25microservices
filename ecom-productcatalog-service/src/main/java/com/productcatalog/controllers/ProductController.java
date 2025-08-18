package com.productcatalog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalog.model.ProductDto;
import com.productcatalog.model.ProductPriceDto;
import com.productcatalog.service.IProductService;

@RestController
@RequestMapping("/product-api/v1")
public class ProductController {
	
		@Autowired
	    private IProductService productService;
	
//		http://localhost:8081/product-api/v1/products
	    @PostMapping("/products")
		ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto) {
			productService.addProduct(productDto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	    
//		http://localhost:8081/product-api/v1/products
	    @PutMapping("/products")
		ResponseEntity<Void> updateProduct(@RequestBody ProductDto productDto){
			productService.updateProduct(productDto);
			HttpHeaders headers =  new HttpHeaders();
			headers.add("info","updating one product");
			headers.add("desc", "update method called");
			return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).build();
		}
	    
//		http://localhost:8081/product-api/v1/products
	    @PatchMapping("/products/newprice")
		ResponseEntity<Void> updateProductPrice(@RequestBody ProductPriceDto productPriceDto){
	    	double price = productPriceDto.getPrice();
	    	int productId = productPriceDto.getProductId();
			productService.updateProductPrice(productId, price);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
	    
//	    http://localhost:8081/product-api/v1/products/productId/2
	    @DeleteMapping("/products/productId/{productId}")
		ResponseEntity<Void> deleteProduct(@PathVariable int productId){
	    	productService.deleteProduct(productId);
	    	HttpHeaders headers =  new HttpHeaders();
			headers.add("info","deleting one product by id");
			return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
	    }
		
//	    http://localhost:8081/product-api/v1/products/productId/1
		@GetMapping("/products/productId/{productId}")
		ResponseEntity<ProductDto> getById(@PathVariable int productId) {
			ProductDto productDto =  productService.getById(productId);
			HttpHeaders headers =  new HttpHeaders();
			headers.add("info","getting one product by id");
			return ResponseEntity.ok().headers(headers).body(productDto);
		}
		
//		 http://localhost:8081/product-api/v1/products
		@GetMapping("/products")
		ResponseEntity<List<ProductDto>> getAll(){
			List<ProductDto> productDtos = productService.getAll();
	    	HttpHeaders headers =  new HttpHeaders();
			headers.add("info","getting all products");
			return ResponseEntity.ok().headers(headers).body(productDtos);
		}
		
//		 http://localhost:8081/product-api/v1/products/brand/Samsung
		@GetMapping("/products/brand/{brand}")
		ResponseEntity<List<ProductDto>> getByBrand(@PathVariable String brand){
			List<ProductDto> productDtos = productService.getByBrand(brand);
	    	HttpHeaders headers =  new HttpHeaders();
			headers.add("info","getting all products by brand");
			return new ResponseEntity<List<ProductDto>>(productDtos, headers, HttpStatus.OK.value());
		}
//		 http://localhost:8081/product-api/v1/products/category?category=electronics
		@GetMapping("/products/category")
		ResponseEntity<List<ProductDto>> getByCategory(@RequestParam String category){
			List<ProductDto> productDtos = productService.getByCategory(category);
	    	HttpHeaders headers =  new HttpHeaders();
			headers.add("info","getting all products by category");
			return ResponseEntity.ok().headers(headers).body(productDtos);
			
		}
//		http://localhost:8081/product-api/v1/products/category/Electronics/price/20000
		@GetMapping("/products/category/{category}/price/{price}")
		ResponseEntity<List<ProductDto>> getByCatLessPrice(@PathVariable String category,@PathVariable double price){
			List<ProductDto> productDtos = productService.getByCatLessPrice(category, price);
	    	HttpHeaders headers =  new HttpHeaders();
			headers.add("info","getting all products by category and less price");
			return ResponseEntity.ok().headers(headers).body(productDtos);
		}

		
		

}
