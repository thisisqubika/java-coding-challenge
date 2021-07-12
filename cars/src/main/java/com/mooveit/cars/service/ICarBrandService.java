package com.mooveit.cars.service;

import java.util.List;

import com.mooveit.cars.dto.CarBrandDto;
import com.mooveit.cars.dto.ModelDto;

public interface ICarBrandService {
	ModelDto getModel(Long modelId);
	List<ModelDto> getModelByBrand(String name);
	CarBrandDto saveCarBrand(String xmlString);
	boolean isCarBrandWithNameExists(String name);

}
