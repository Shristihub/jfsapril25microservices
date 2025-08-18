package com.productinventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productinventory.model.InventoryRequest;
import com.productinventory.service.IInventoryService;

@RestController
@RequestMapping("/inventory-service/v1")
public class InventoryController {
	
	@Autowired
	private IInventoryService inventoryService;

//	http://localhost:8085/inventory-service/v1/inventories?productId=102&stock=100
	@GetMapping("/inventories")
	ResponseEntity<Void> addStock(@RequestParam int productId,@RequestParam int stock){
		InventoryRequest request = new InventoryRequest();
		request.setProductId(productId);
		request.setStock(stock);
		inventoryService.addStock(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
//	http://localhost:8085/inventory-service/v1/inventories/productId/102
	@GetMapping("/inventories/productId/{productId}")
	ResponseEntity<Integer> checkStock(@PathVariable int productId) {
		return null;
	}
	@PutMapping("/inventories")
	String updateStock(InventoryRequest inventoryRequest) {
		return null;
	}
	
}
