/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mooveit.cars.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.mooveit.cars.domain.CarModel;
import com.mooveit.cars.dto.CarModelDTO;
import com.mooveit.cars.repositories.CarDAO;
import com.mooveit.cars.repositories.CarSubModelDAO;

/**
 *
 * @author dfhm0371
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServicesImplementTest {
	
	@InjectMocks
	CarServicesImplement carService;
	
	@Mock
	private CarDAO carDAO;
	
	@Mock
	private CarSubModelDAO carSubDAO;
	
    public CarServicesImplementTest() {
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
     * Test of findAllCars method, of class CarServicesImplement.
     */
    @Test
    public void testFindAllCarsNotFound() {
        System.out.println("findAllCars");
        ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(HttpStatus.NOT_FOUND);

		List<CarModel> carModels = null;
		
		when(carDAO.findAll()).thenReturn(carModels);

		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<CarModelDTO> result = carService.findAllCars();

		assertEquals(expResult.getStatusCode(), result.getStatusCode());		
    }

    @Test
    public void testFindAllCars() {
        System.out.println("findAllCars");
        ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(HttpStatus.OK);

        List<CarModel> carModels = new ArrayList<CarModel>();
		CarModel car = new CarModel();
		car.setId(new Integer(1));
		carModels.add(car);
		
		when(carDAO.findAll()).thenReturn(carModels);

		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<CarModelDTO> result = carService.findAllCars();

		assertEquals(expResult.getStatusCode(), result.getStatusCode());		
    }
    
    /**
     * Test of findCarsByBrand method, of class CarServicesImplement.
     */
    @Test
    public void testFindCarsByBrand() {
        System.out.println("findCarsByBrand");
        ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(HttpStatus.OK);
        String brand="Test";
		List<CarModel> carModels = new ArrayList<CarModel>();
		CarModel car = new CarModel();
		car.setId(new Integer(1));
		car.setBrand("Test");
		carModels.add(car);
		when(carDAO.findByBrand(brand)).thenReturn(carModels);

		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<CarModelDTO> result = carService.findCarsByBrand(brand);

		assertEquals(expResult.getStatusCode(), result.getStatusCode());	
    }

    @Test
    public void testFindCarsByBrandNotFound() {
        System.out.println("findCarsByBrand");
        ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(HttpStatus.NOT_FOUND);
        String brand="Test";
		List<CarModel> carModels = null;
		
		when(carDAO.findByBrand(brand)).thenReturn(carModels);

		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<CarModelDTO> result = carService.findCarsByBrand(brand);

		assertEquals(expResult.getStatusCode(), result.getStatusCode());	
    }
    /**
     * Test of findCarById method, of class CarServicesImplement.
     */
    @Test
    public void testFindCarById() {
        System.out.println("findCarById");
        ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(HttpStatus.OK);
        int id=1;
		CarModel car = new CarModel();
		car.setId(new Integer(id));
		when(carDAO.findById(id)).thenReturn(car);

		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<CarModelDTO> result = carService.findCarById(id);

		assertEquals(expResult.getStatusCode(), result.getStatusCode());	
    }

    @Test
    public void testFindCarByIdNotFound() {
        System.out.println("findCarById");
        ResponseEntity<CarModelDTO> expResult = new ResponseEntity<CarModelDTO>(HttpStatus.NOT_FOUND);
        int id=0;
		
		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<CarModelDTO> result = carService.findCarById(id);

		assertEquals(expResult.getStatusCode(), result.getStatusCode());	
    }
    
    /**
     * Test of createXMLCarModel method, of class CarServicesImplement.
     */
    @Test
    public void testCreateXMLCarModelFail() throws Exception {
        System.out.println("createXMLCarModel");
        String xml = "";
        ResponseEntity<String> expResult = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        
		
		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<String> result = carService.createXMLCarModel(xml);

		assertEquals(expResult.getStatusCode(), result.getStatusCode());	
    }

    /**
     * Test of deleteCarById method, of class CarServicesImplement.
     */
    @Test
    public void testDeleteCarById() {
        System.out.println("deleteCarById");
        int id = 1;
        ResponseEntity<String> expResult = new ResponseEntity<String>(HttpStatus.OK);
        CarModel car = new CarModel();
		car.setId(new Integer(1));
		when(carDAO.findById(id)).thenReturn(car);
		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<String> result = carService.deleteCarById(id);

		assertEquals(expResult.getStatusCode(), result.getStatusCode());	
    }
    
    @Test
    public void testDeleteCarByIdFail() {
        System.out.println("deleteCarById");
        int id = 0;
        ResponseEntity<String> expResult = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        
		ReflectionTestUtils.setField(carService, "carDAO",
				carDAO);

		ResponseEntity<String> result = carService.deleteCarById(id);

		assertEquals(expResult.getStatusCode(), result.getStatusCode());	
    }
}
