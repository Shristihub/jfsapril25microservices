package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ICourseService;

@RestController
public class GreetController {
	@Autowired
//	@Qualifier("webCourses")
	private ICourseService courseService;

	@GetMapping("/say-hello/{username}")
	ResponseEntity<String> sayHello(@PathVariable("username") String username){
		String message = "Hello "+username;
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/show-courses")
	ResponseEntity<List<String>> printCourses(){
		List<String> courses =  courseService.showCourses();
		return ResponseEntity.ok(courses);
	}
}
