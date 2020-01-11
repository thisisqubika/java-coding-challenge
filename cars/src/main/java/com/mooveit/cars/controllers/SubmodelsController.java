package com.mooveit.cars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.domain.Submodel;
import com.mooveit.cars.repositories.SubmodelRepository;

@RestController
@RequestMapping("api/v1/submodels")
public class SubmodelsController {
	
	@Autowired
	private SubmodelRepository submodelRepository;
	
	@GetMapping
	public List<Submodel> list() {
		return submodelRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Submodel get(@PathVariable Long id) {
		return submodelRepository.getOne(id);
	}
	
	@GetMapping
	@RequestMapping("/brand/{name}")
	public List<Submodel> getBrand(@PathVariable String name) {
		return submodelRepository.getCarSpecificationByBrand(name.toLowerCase());
	}
}
