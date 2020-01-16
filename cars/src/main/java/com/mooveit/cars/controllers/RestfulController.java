package com.mooveit.cars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.services.ModelServiceI;

@RestController
public class RestfulController {

	@Autowired
	ModelServiceI modelService;

	@GetMapping("/models/{id}")
	Model modelById(@PathVariable Integer id) {

		return modelService.getModelById(id).orElse(null);

	}

	@GetMapping("/models")
	List<Model> modelsByBrandName(@RequestParam String brandname) {

		return modelService.getModelsByBrandName(brandname);

	}	

}
