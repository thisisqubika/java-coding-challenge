package com.mooveit.cars.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.repositories.ModelRepositoryI;

@Service
public class ModelService implements ModelServiceI {

	@Autowired
	ModelRepositoryI repository;
	
	@Override
	public Optional<Model> getModelById(Integer id) {
		
		return repository.findById(id);
		
	}

	@Override
	public List<Model> getModelsByBrandName(String brandName) {
		
		return repository.findByBrandName(brandName);
		
	}	

}
