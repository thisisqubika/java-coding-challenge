package com.mooveit.cars.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.dto.CarModelDTO;
import com.mooveit.cars.utilities.CarUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServicesImplementIT {

	@Autowired
	CarServices service;

	@Test
	public void findAllCarSubModelTest() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ford-example.xml");

			if (inputStream != null) {
				service.createXMLCarModel(CarUtility.InputStreamToString(inputStream));
			}

			ResponseEntity<CarModelDTO> expResult = service.findAllCars();

			assertTrue(expResult.getBody().getSuccess());
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void createCarCatalogue() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ford-example.xml");

			if (inputStream != null) {
				ResponseEntity<String> expResult = service.createXMLCarModel(CarUtility.InputStreamToString(inputStream));

				assertTrue(expResult.getBody().equals("OK"));
			}
			assertTrue(true);
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void createCarCatalogueWhitOutParams() {
		try {

			ResponseEntity<String> expResult = service.createXMLCarModel(null);

			assertTrue(expResult.getStatusCode().equals(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void createCarCatalogueBadlyFormedParameter() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ford-exampleBad.xml");

			if (inputStream != null) {
				ResponseEntity<String> expResult = service.createXMLCarModel(CarUtility.InputStreamToString(inputStream));
				assertTrue(expResult.getStatusCode().equals(HttpStatus.NOT_FOUND));
			}			
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void findCarById() {
		try {
			ResponseEntity<CarModelDTO> expResult = service.findCarById(1);

			assertTrue(expResult.getBody().getSuccess());
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void findCarByIdZero() {
		try {
			ResponseEntity<CarModelDTO> expResult = service.findCarById(0);
			assertTrue(expResult.getStatusCode().equals(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void findCarByBrand() {
		try {
			ResponseEntity<CarModelDTO> expResult =  service.findCarsByBrand("Ford");

			assertTrue(expResult.getBody().getSuccess());
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void deleteCarByIdNotExist() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ford-example.xml");

			if (inputStream != null) {
				service.createXMLCarModel(CarUtility.InputStreamToString(inputStream));
			}
			ResponseEntity<String> expResult =service.deleteCarById(1125);
			assertTrue(expResult.getStatusCode().equals(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
			}
	}

	@Test
	public void deleteCarByIdZero() {
		try {
			ResponseEntity<String> expResult =service.deleteCarById(0);
			assertTrue(expResult.getStatusCode().equals(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
			}
	}
}
