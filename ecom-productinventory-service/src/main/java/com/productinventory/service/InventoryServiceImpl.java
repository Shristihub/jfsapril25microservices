package com.productinventory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productinventory.exceptions.InvalidProductIdException;
import com.productinventory.model.Inventory;
import com.productinventory.model.InventoryRequest;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private IInventoryRepository inventoryRepository;

	@Override
	public void addStock(InventoryRequest inventoryRequest) {
		// get the productId from the inventory
		int productId = inventoryRequest.getProductId();
		// check if it is in db
		Inventory inventory = inventoryRepository.findByProductId(productId) // return inventory if present
				.orElse(new Inventory()); // eles create a new inventory object
		// get the productId, stock from inventoryrequest and set it
		// if the inventory exists already, get the old staock also
		inventory.setStock(inventoryRequest.getStock() + inventory.getStock());
		inventory.setProductId(productId);
		// save the inventory object
		inventoryRepository.save(inventory);

	}

	@Override
	public int checkStock(int productId) {
		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new InvalidProductIdException("invalid id"));
		return inventory.getStock();
	}

	@Override
	public String updateStock(InventoryRequest inventoryRequest) {
		// get the productId from the inventory
		int productId = inventoryRequest.getProductId();
		// check if it is in db
		Inventory inventory = inventoryRepository.findByProductId(productId) // return inventory if present
				.orElseThrow(() -> new InvalidProductIdException("invalid id"));
		// get the productId, stock from inventoryrequest and set it
		// if the inventory exists already, get the old staock also
		inventory.setStock(inventoryRequest.getStock() + inventory.getStock());
		inventory.setProductId(productId);
		// update the inventory object
		inventoryRepository.save(inventory);
		return "stock updated";
	}

}
