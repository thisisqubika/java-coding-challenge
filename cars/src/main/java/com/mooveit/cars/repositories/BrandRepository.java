package com.mooveit.cars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

	List<Brand> findByName(String name);	
	
}
