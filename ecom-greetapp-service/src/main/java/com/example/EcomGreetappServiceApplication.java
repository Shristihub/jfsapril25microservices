package com.example;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class EcomGreetappServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EcomGreetappServiceApplication.class, args);
	}

	@Autowired
	private ApplicationContext context;
	@Override
	public void run(String... args) throws Exception {
		Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
