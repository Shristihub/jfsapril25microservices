package com.productinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EcomProductinventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomProductinventoryServiceApplication.class, args);
	}

	
	
}
