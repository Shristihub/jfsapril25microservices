package com.productinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.productinfo.model.Product;

@Service
public class ProductInfoServiceImplload implements IProductInfoService{

	@Autowired
	private RestTemplate template;
	// pass the application name instead of localhost
	private final String BASEURL = "http://product-catalog/product-api/v1/products";
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Override
	public Product getById(int productId)  {
		ServiceInstance serviceInstance =  loadBalancerClient.choose("product-catalog");
		
		System.out.println("............");
		System.out.println("port "+serviceInstance.getPort());
		System.out.println("host"+serviceInstance.getHost());
		System.out.println("scheme "+serviceInstance.getScheme());
		System.out.println("instanceid"+serviceInstance.getInstanceId());
		System.out.println("meta "+serviceInstance.getMetadata());
		System.out.println("serviceId"+serviceInstance.getServiceId());
		System.out.println("uri"+serviceInstance.getUri());
		
		
		
//		 http://localhost:8081/product-api/v1/products/productId/1
		String url = BASEURL.concat("/productId/")+productId;
		ResponseEntity <Product> productEntity =  template.getForEntity(url, Product.class);
		return productEntity.getBody();
	}

	@Override
	public List<Product> getAll() {
		
		List<ServiceInstance> instances = discoveryClient.getInstances("product-catalog");
		instances.forEach(serviceInstance->{
			System.out.println("............");
			System.out.println("port "+serviceInstance.getPort());
			System.out.println("host"+serviceInstance.getHost());
			System.out.println("scheme "+serviceInstance.getScheme());
			System.out.println("instanceid"+serviceInstance.getInstanceId());
			System.out.println("meta "+serviceInstance.getMetadata());
			System.out.println("serviceId"+serviceInstance.getServiceId());
			System.out.println("uri"+serviceInstance.getUri());
			
		});
		
//		http://localhost:8081/product-api/v1/products
		ResponseEntity<List> productEntity =  template.getForEntity(BASEURL,List.class);
		List<Product> products = productEntity.getBody();
		System.out.println("........"+productEntity.getStatusCode()+".......");
		return products;
	}

	@Override
	public List<Product> getByBrand(String mbrand)  {
//		 http://localhost:8081/product-api/v1/products/brand/Samsung
		String url = BASEURL.concat("/brand/").concat(mbrand);
		ResponseEntity<List> productEntity =  template.getForEntity(url,List.class);
		List<Product> products = productEntity.getBody();
		System.out.println("........"+productEntity.getStatusCode()+".......");
		return products;
	}

	@Override
	public List<Product> getByCategory(String mcategory)  {
//		http://localhost:8081/product-api/v1/products/category?category=electronics
		String url = BASEURL.concat("/category?category=").concat(mcategory);
		ResponseEntity<List> productEntity =  template.getForEntity(url,List.class);
		List<Product> products = productEntity.getBody();
		System.out.println("........"+productEntity.getStatusCode()+".......");
		return products;
	}

}
