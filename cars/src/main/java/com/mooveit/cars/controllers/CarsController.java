package com.mooveit.cars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.domain.ModelTable;
import com.mooveit.cars.dto.ModelDTO;
import com.mooveit.cars.dto.ModelListDTO;
import com.mooveit.cars.service.CarsService;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@RestController
@RequestMapping("/cars")
public class CarsController {

	@Autowired
	private CarsService carsService;

	/**
	 * Get all cars models
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public List<ModelTable> getCars() {
		return carsService.getModels();
	}

	/**
	 * Get car model by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}")
	public ModelDTO getCar(@PathVariable final Long id) {
		return carsService.getModel(id);
	}

	/**
	 * Get car models by brand
	 * 
	 * @param brand
	 * @return
	 */
	@RequestMapping("/byBrand/{brand}")
	public ModelListDTO getCarByBrand(@PathVariable final String brand) {
		return carsService.getModelByBrand(brand);
	}

}
