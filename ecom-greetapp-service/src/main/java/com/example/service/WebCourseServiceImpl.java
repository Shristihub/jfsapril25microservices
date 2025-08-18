package com.example.service;

import java.util.Arrays;
import java.util.List;

public class WebCourseServiceImpl implements ICourseService{

	@Override
	public List<String> showCourses() {
		return Arrays.asList("HTML","CSS","JS","React");
	}

}
