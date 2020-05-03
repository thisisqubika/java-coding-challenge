package com.mooveit.cars.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.service.SubModelService;

@RestController
@RequestMapping("/cars")
public class SubModelController {

	private final SubModelService modelService;

	public SubModelController(SubModelService modelService) {
		super();
		this.modelService = modelService;
	}

	@GetMapping("/by_id")
	public Object getCarModelById(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return modelService.getModelById(id);
	}
	
	@GetMapping("/by_brand")
	public Object getCarsByBrand (@RequestParam(value = "brandName") String brandName) {
		return modelService.getAllModelsByBrandName(brandName);
	}

}
