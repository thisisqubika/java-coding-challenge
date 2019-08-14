package com.mooveit.cars.service;

import java.util.List;

import com.mooveit.cars.domain.ModelTable;
import com.mooveit.cars.dto.ModelDTO;
import com.mooveit.cars.dto.ModelListDTO;

/**
 * Service methods for RESTful API
 * 
 * @author Lucas Arquiel
 *
 */
public interface CarsService {
	/**
	 * Get all car models
	 * 
	 * @return
	 */
	List<ModelTable> getModels();

	/**
	 * Get car model by id
	 * 
	 * @param id
	 * @return
	 */
	ModelDTO getModel(final Long id);

	/**
	 * Get car model by brand
	 * 
	 * @param brand
	 * @return
	 */
	ModelListDTO getModelByBrand(final String brand);

}
