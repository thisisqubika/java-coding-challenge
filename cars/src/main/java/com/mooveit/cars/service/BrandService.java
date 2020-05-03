package com.mooveit.cars.service;

import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.repositories.BrandRepository;

@Service
public class BrandService {

	private final BrandRepository brandRepo;

	public BrandService(BrandRepository brandRepo) {
		super();
		this.brandRepo = brandRepo;
	}
	
	public Brand findOneBrandByName (String brandName) {
		return brandRepo.findByName(brandName).stream().findFirst().orElse(new Brand());
	}
}
