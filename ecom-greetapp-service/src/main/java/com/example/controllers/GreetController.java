package com.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

	@GetMapping("/say-hello/{username}")
	ResponseEntity<String> sayHello(@PathVariable("username") String username){
		String message = "Hello "+username;
		return ResponseEntity.ok(message);
	}
}
