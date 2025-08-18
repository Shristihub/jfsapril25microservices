package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.example.service.CloudCourseServiceImpl;
import com.example.service.WebCourseServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	@Profile("dev")
	CloudCourseServiceImpl cloudCourse() {
		return new CloudCourseServiceImpl();
	}
	
	@Bean
//	@Primary
	@Profile("prod")
	WebCourseServiceImpl webCourses() {
		return new WebCourseServiceImpl();
	}
}
