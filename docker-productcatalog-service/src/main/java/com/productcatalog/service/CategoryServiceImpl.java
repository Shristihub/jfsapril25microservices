package com.productcatalog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcatalog.model.Category;
import com.productcatalog.model.CategoryDto;
import com.productcatalog.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryRepository categoryRepository;
	@Autowired
	private ModelMapper mapper;
	@Override
	public void addCategory(CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		categoryRepository.save(category);	
	}

	@Override
	public void deleteCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
		
	}

	@Override
	public CategoryDto getById(int categoryId) {
		Category category = categoryRepository.findById(categoryId)
				  .orElseThrow(()-> new RuntimeException("invalid category"));
				return mapper.map(category, CategoryDto.class);
	}

}
