package com.mooveit.cars.service;

import java.util.List;

import com.mooveit.cars.domain.CarModel;

public interface CarServices {

	List<CarModel> findAll();

	List<CarModel> findByBrand(String brand);

	CarModel findById(int id);

	void createXMLModel(String xml);
}
