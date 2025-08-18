package com.example.service;

import java.util.Arrays;
import java.util.List;

public class CloudCourseServiceImpl implements ICourseService{

	@Override
	public List<String> showCourses() {
		return Arrays.asList("aws","gcp","azure");
	}

}
