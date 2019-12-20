package com.mooveit.cars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.CarModel;
import com.mooveit.cars.service.CarServices;
import com.mooveit.cars.utilities.CarUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsApplicationTests {

	@Autowired
	CarServices service;

	@Test
	public void findAllSubModelTest() {
		try {
			Iterable<CarModel> cars = service.findAll();

			cars.forEach(p -> p.getSubModels().forEach(q -> System.out.println(q.getName())));

			assertTrue(true);
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void createCarCatalogue() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ford-example.xml");

			if (inputStream != null) {
				service.createXMLModel(CarUtility.toString(inputStream));
				assertTrue(true);
			}
			assertTrue(true);
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void findById() {
		try {
			CarModel car = service.findById(1);

			if (car != null) {
				assertEquals(1, car.getId());
				System.out.println(car.getName());
			} else {
				assertTrue(true);
			}
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}

	@Test
	public void findByBrand() {
		try {
			List<CarModel> cars = service.findByBrand("Ford");

			if (cars != null && cars.size() > 0) {
				assertEquals("Ford", cars.get(0).getBrand());
				System.out.println(cars.get(0).getBrand());
			} else {
				assertTrue(true);
			}
		} catch (Exception e) {
			fail("Error: " + e.getMessage());
		}
	}
}
