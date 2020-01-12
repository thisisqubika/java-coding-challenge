/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mooveit.cars.controller;

import com.mooveit.cars.domain.CarModel;
import com.mooveit.cars.dto.CarModelDTO;
import com.mooveit.cars.service.CarServices;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author dfhm0371
 */
@RunWith(MockitoJUnitRunner.class)
public class CarsRestControllerTest {

	@InjectMocks
	CarsRestController carController;

	@Mock
	CarServices carService;

	public CarsRestControllerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of findAllCars method, of class CarsRestController.
	 */
	@Test
	public void testFindAllCars() {
		System.out.println("findAllCars");

		List<CarModel> carList = new ArrayList<CarModel>();
		CarModelDTO dto = new CarModelDTO();
		CarModel carResult = new CarModel();
		carResult.setId(new Integer(1));
		carResult.setBrand("Ford");
		carResult.setFrom(new Integer(2019));
		carResult.setTo(new Integer(2020));
		carList.add(carResult);
		dto.setCars(carList);
		dto.setMessage("OK");
		dto.setSuccess(true);
		ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		when(carService.findAllCars()).thenReturn(expResult);

		ReflectionTestUtils.setField(carController, "carService", carService);

		ResponseEntity<CarModelDTO> result = carController.findAllCars();
		assertEquals(expResult.getStatusCode(), result.getStatusCode());
	}

	/**
	 * Test of getCarById method, of class CarsRestController.
	 */
	@Test
	public void testGetCarById() {
		System.out.println("getCarById");
		int carId = 1;
		List<CarModel> carList = new ArrayList<CarModel>();
		CarModelDTO dto = new CarModelDTO();
		CarModel carResult = new CarModel();
		carResult.setId(new Integer(1));
		carResult.setBrand("Ford");
		carResult.setFrom(new Integer(2019));
		carResult.setTo(new Integer(2020));
		carList.add(carResult);
		dto.setCars(carList);
		dto.setMessage("OK");
		dto.setSuccess(true);
		ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		when(carService.findCarById(carId)).thenReturn(expResult);

		ReflectionTestUtils.setField(carController, "carService", carService);

		ResponseEntity<CarModelDTO> result = carController.getCarById(carId);
		assertEquals(expResult.getStatusCode(), result.getStatusCode());
	}

	/**
	 * Test of createXMLCarModel method, of class CarsRestController.
	 */
	@Test
	public void testCreateXMLCarModel() {
		System.out.println("createXMLCarModel");

		String xml = null;

		ResponseEntity<String> expResult = new ResponseEntity<String>("Error", HttpStatus.NOT_FOUND);
		when(carService.createXMLCarModel(xml)).thenReturn(expResult);

		ReflectionTestUtils.setField(carController, "carService", carService);

		ResponseEntity<String> result = carController.createXMLCarModel(xml);
		assertEquals(expResult.getStatusCode(), result.getStatusCode());
	}

	/**
	 * Test of findCarsByBrand method, of class CarsRestController.
	 */
	@Test
	public void testFindCarsByBrand() {
		System.out.println("findCarsByBrand");
		String brand = "Ford";
		List<CarModel> carList = new ArrayList<CarModel>();
		CarModelDTO dto = new CarModelDTO();
		CarModel carResult = new CarModel();
		carResult.setId(new Integer(1));
		carResult.setBrand("Ford");
		carResult.setFrom(new Integer(2019));
		carResult.setTo(new Integer(2020));
		carList.add(carResult);
		dto.setCars(carList);
		dto.setMessage("OK");
		dto.setSuccess(true);
		ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		when(carService.findCarsByBrand(brand)).thenReturn(expResult);

		ReflectionTestUtils.setField(carController, "carService", carService);

		ResponseEntity<CarModelDTO> result = carController.findCarsByBrand(brand);
		assertEquals(expResult.getStatusCode(), result.getStatusCode());
	}

	/**
	 * Test of deleteCarById method, of class CarsRestController.
	 */
	@Test
	public void testDeleteCarById() {
		System.out.println("deleteCarById");
		int id = 1;
		ResponseEntity<String> expResult = new ResponseEntity<String>("OK", HttpStatus.OK);
		when(carService.deleteCarById(id)).thenReturn(expResult);

		ReflectionTestUtils.setField(carController, "carService", carService);

		ResponseEntity<String> result = carController.deleteCarById(id);
		assertEquals(expResult.getStatusCode(), result.getStatusCode());
	}
}
