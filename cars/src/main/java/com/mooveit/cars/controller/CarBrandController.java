package com.mooveit.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.dto.ModelDto;
import com.mooveit.cars.service.ICarBrandService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CarBrandController {

	private final ICarBrandService carModelService;

	@Autowired
	public CarBrandController(ICarBrandService carModelService) {
		this.carModelService = carModelService;
	}

	@GetMapping(value = "/cars/{modelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ModelDto> getModel(@PathVariable Long modelId) {
		log.debug("@@@@@ Entering 'REST::getModel' method with modelId param -> {}", modelId);
		return new ResponseEntity<>(carModelService.getModel(modelId), HttpStatus.OK);
	}

	@GetMapping(value = "/cars/byBrand/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ModelDto>>getModelByBrand(@PathVariable String name) {
		log.debug("@@@@@ Entering 'REST::getModelByBrand' method with name param -> {}", name);
		return new ResponseEntity<>(carModelService.getModelByBrand(name), HttpStatus.OK);
	}

}
