package com.productinventory.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productinventory.model.Inventory;


public interface IInventoryRepository extends JpaRepository<Inventory, Integer>{

	Optional<Inventory> findByProductId(int productId);
}
