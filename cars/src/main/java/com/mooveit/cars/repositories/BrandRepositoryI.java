package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Brand;

public interface BrandRepositoryI extends JpaRepository<Brand, Integer> {
	
	Brand getByName(String name);

}
