package com.mooveit.cars.services;

import java.util.List;
import java.util.Optional;

import com.mooveit.cars.domain.Model;

public interface ModelServiceI {

	Optional<Model> getModelById(Integer id);
	
	List<Model> getModelsByBrandName(String brandName);
	
}
