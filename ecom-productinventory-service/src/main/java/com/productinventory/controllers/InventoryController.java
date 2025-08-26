package com.productinventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productinventory.model.InventoryRequest;
import com.productinventory.service.IInventoryService;

@RestController
@RequestMapping("/inventory-service/v1")
public class InventoryController {

	@Autowired
	private IInventoryService inventoryService;

//	http://localhost:8087/inventory-service/v1/inventories
	@PostMapping("/inventories")
	ResponseEntity<String> addStock(@RequestBody InventoryRequest inventoryRequest) {
		String result  = inventoryService.addStock(inventoryRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

//	http://localhost:8087/inventory-service/v1/inventories/productId/102
	@GetMapping("/inventories/productId/{productId}")
	ResponseEntity<Integer> checkStock(@PathVariable int productId) {
		int stock = inventoryService.checkStock(productId);
		return ResponseEntity.ok(stock);
	}

	@PutMapping("/inventories")
	ResponseEntity<String> updateStock(@RequestBody InventoryRequest inventoryRequest) {
		String result  = inventoryService.updateStock(inventoryRequest);
		return ResponseEntity.ok(result);
	}

}
