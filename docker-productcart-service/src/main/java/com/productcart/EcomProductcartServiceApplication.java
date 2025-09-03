package com.productcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcomProductcartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomProductcartServiceApplication.class, args);
	}

}
