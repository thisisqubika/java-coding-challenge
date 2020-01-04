package com.mooveit.cars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.repositories.ModelRepository;

@RestController
@RequestMapping("api/v1/models")
public class ModelsController {

	@Autowired
	private ModelRepository modelRepository;

	@GetMapping
	public List<Model> list() {
		return modelRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Model get(@PathVariable Long id) {
		return modelRepository.getOne(id);
	}

	@GetMapping
	@RequestMapping("/brand/{model_name}")
	public List<Model> getBrand(@PathVariable String model_name) {
		return modelRepository.getCarSpecificationByBrand(model_name);
	}

	@PostMapping
	public Model create(@RequestBody final Model model) {
		return modelRepository.saveAndFlush(model);
	}
}