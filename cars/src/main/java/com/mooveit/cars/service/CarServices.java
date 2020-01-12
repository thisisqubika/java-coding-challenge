package com.mooveit.cars.service;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.http.ResponseEntity;

import com.mooveit.cars.domain.CarModel;
import com.mooveit.cars.dto.CarModelDTO;

public interface CarServices {

	ResponseEntity<CarModelDTO> findAllCars();

	ResponseEntity<CarModelDTO> findCarsByBrand(String brand);

	ResponseEntity<CarModelDTO> findCarById(int id);

	ResponseEntity<String> createXMLCarModel(String xml);
	
	ResponseEntity<String> deleteCarById(int id);
}
