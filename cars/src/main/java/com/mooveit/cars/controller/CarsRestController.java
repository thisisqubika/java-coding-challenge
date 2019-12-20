package com.mooveit.cars.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api")
public class CarsRestController {

	@Autowired
	private CarServices carService;

	@RequestMapping(value = "cars", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
	public ResponseEntity<CarModelDTO> findAll() {
		CarModelDTO dto = new CarModelDTO();
		try {
			Iterable<CarModel> cars = carService.findAll();
			if (cars == null) {
				dto.setSuccess(false);
				dto.setMessage("NO FOUND CARS");
				dto.setCars(null);
			} else {
				dto.setSuccess(true);
				dto.setMessage("OK");
				dto.setCars(cars);
			}
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "carId", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json", params = { "carId" })
	public ResponseEntity<CarModelDTO> getCar(@RequestParam(value = "carId") int carId) {
		CarModelDTO dto = new CarModelDTO();
		List<CarModel> list = new ArrayList<CarModel>();
		try {
			CarModel car = carService.findById(carId);
			if (car == null) {
				dto.setSuccess(false);
				dto.setMessage("NO FOUND CARS");
				dto.setCars(null);
			} else {
				list.add(car);
				dto.setSuccess(true);
				dto.setMessage("OK");
				dto.setCars(list);
			}
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "createXmlCars", method = RequestMethod.POST, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json", consumes = "text/xml")
	public ResponseEntity<String> createXMLModel(@RequestBody String xml) {
		try {
			carService.createXMLModel(xml);
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("ERROR" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "carsBrand", method = RequestMethod.GET, produces = {
			MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json", params = { "brand" })
	public ResponseEntity<CarModelDTO> findByBrand(@RequestParam(value = "brand") String brand) {
		CarModelDTO dto = new CarModelDTO();
		try {
			List<CarModel> cars = carService.findByBrand(brand);
			if (cars == null) {
				dto.setSuccess(false);
				dto.setMessage("NO FOUND CARS");
				dto.setCars(null);
			} else {
				dto.setSuccess(true);
				dto.setMessage("OK");
				dto.setCars(cars);
			}
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		}
	}
}
