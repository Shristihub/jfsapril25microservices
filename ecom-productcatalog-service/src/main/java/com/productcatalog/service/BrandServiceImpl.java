package com.productcatalog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcatalog.exceptions.BrandNotFoundException;
import com.productcatalog.model.Brand;
import com.productcatalog.model.BrandDto;
import com.productcatalog.repository.IBrandRepository;

@Service
public class BrandServiceImpl implements IBrandService{
	@Autowired
	private IBrandRepository brandRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public void addBrand(BrandDto brandDto) {
		Brand brand = mapper.map(brandDto, Brand.class);
		brandRepository.save(brand);
	}

	@Override
	public void updateBrand(BrandDto brandDto) {
		Brand brand = mapper.map(brandDto, Brand.class);
		brandRepository.save(brand);		
	}

	@Override
	public void deleteBrand(int brandId) {
		brandRepository.deleteById(brandId);
		
	}

	@Override
	public BrandDto getById(int brandId) throws BrandNotFoundException {
		Brand brand = brandRepository.findById(brandId)
		  .orElseThrow(()-> new BrandNotFoundException("invalid brand"));
		return mapper.map(brand, BrandDto.class);
	}

	
}
