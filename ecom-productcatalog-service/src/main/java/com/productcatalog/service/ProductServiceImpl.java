package com.productcatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.productcatalog.exceptions.ProductNotFoundException;
import com.productcatalog.model.InventoryDto;
import com.productcatalog.model.Product;
import com.productcatalog.model.ProductDto;
import com.productcatalog.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RestTemplate restTemplate;

	private final String BASEURI = "http://product-inventory/inventory-service/v1/inventories";

	@Override
	public void addProduct(ProductDto productDto) {
		// convert dto(productDto) in entity(product)

		Product product = mapper.map(productDto, Product.class);
		Product savedProduct = productRepository.save(product);
		// get the stock from the productDTO
		int stock = productDto.getStock();
		// get the productId after saving the product
		int productId = savedProduct.getProductId();
		// create the inventoryDTo object
		InventoryDto inventoryDto = new InventoryDto();
		inventoryDto.setProductId(productId);
		inventoryDto.setStock(stock);
		// frame the uri to call the inventory microservice
		// using resttemplate call the appropriate method
		ResponseEntity<String> response = restTemplate.postForEntity(BASEURI, inventoryDto, String.class);
		System.out.println(response.getBody());
	}

	@Override
	public void updateProduct(ProductDto productDto) {
		// convert dto(productDto) in entity(product)
		Product product = mapper.map(productDto, Product.class);
		// product should have id in it - then updates it
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);

	}

	@Override
	public ProductDto getById(int productId) {
//		Optional<Product> productOpt =  productRepository.findById(productId);
//		//check if opt is having value product entity
//		if(productOpt.isPresent()) {
//			// this returns the product entity
//			Product product = productOpt.get();
//			//convert the entity into productDto object
//			ProductDto productDto =mapper.map(product, ProductDto.class);
//			return productDto;
//		}
//		return null;
		// get the value if present or throw exception
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("invalid id"));
		// convert the entity into productDto object
		ProductDto productDto = mapper.map(product, ProductDto.class);
		return productDto;

	}

	@Override
	public List<ProductDto> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> mapper.map(product, ProductDto.class))
				.sorted((p1, p2) -> p1.getProductName().compareTo(p2.getProductName())).collect(Collectors.toList());

	}

	@Override
	public List<ProductDto> getByBrand(String brand) throws ProductNotFoundException {
		List<Product> products = productRepository.findByBrand(brand);
		if (products.isEmpty())
			throw new ProductNotFoundException("product with this brand not available");
		return products.stream().map(product -> mapper.map(product, ProductDto.class))
				.sorted((p1, p2) -> p1.getProductName().compareTo(p2.getProductName())).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getByCategory(String category) throws ProductNotFoundException {
		List<Product> products = productRepository.findByCategory(category);
		if (products.isEmpty())
			throw new ProductNotFoundException("product with this category not available");
		return products.stream().map(product -> mapper.map(product, ProductDto.class))
				.sorted((p1, p2) -> p1.getProductName().compareTo(p2.getProductName())).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getByCatLessPrice(String category, double price) throws ProductNotFoundException {
		List<Product> products = productRepository.findByCategoryPrice(category, price);
		if (products.isEmpty())
			throw new ProductNotFoundException("product with this category and less price not available");
		return products.stream().map(product -> mapper.map(product, ProductDto.class))
				.sorted((p1, p2) -> p1.getProductName().compareTo(p2.getProductName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void updateProductStock(int productId, int stock) {
		//call inventoryREST APi to update stock
		// create the inventoryDTo object
		InventoryDto inventoryDto = new InventoryDto();
		inventoryDto.setProductId(productId);
		inventoryDto.setStock(stock);
		restTemplate.put(BASEURI, inventoryDto);
	}

}
