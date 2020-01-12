package com.mooveit.cars.controller;

import java.lang.reflect.MalformedParametersException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.domain.CarModel;
import com.mooveit.cars.dto.CarModelDTO;
import com.mooveit.cars.service.CarServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/carsServices")
public class CarsRestController {

	@Autowired
	private CarServices carService;

	@RequestMapping(value = "cars", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
	public ResponseEntity<CarModelDTO> findAllCars() {
		return carService.findAllCars();
	}

	@RequestMapping(value = "carId", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json", params = { "carId" })
	public ResponseEntity<CarModelDTO> getCarById(@RequestParam(value = "carId") int carId) {
		return carService.findCarById(carId);
	}

	@RequestMapping(value = "createXmlCars", method = RequestMethod.POST, consumes = "text/xml")
	public ResponseEntity<String> createXMLCarModel(@RequestBody String xml) {
		return carService.createXMLCarModel(xml);
	}

	@RequestMapping(value = "carsBrand", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json", params = { "brand" })
	public ResponseEntity<CarModelDTO> findCarsByBrand(@RequestParam(value = "brand") String brand) {
		return carService.findCarsByBrand(brand);
	}

	@RequestMapping(value = "isAlive", method = RequestMethod.GET)
	public ResponseEntity<String> isAlive() {
		try {
			return new ResponseEntity<String>("Is Alive", HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>("ERROR: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "deleteCarById", method = RequestMethod.DELETE,params = { "id" } )
	public ResponseEntity<String> deleteCarById(@RequestParam(value = "id") int id) {
		return carService.deleteCarById(id);
	}

}
