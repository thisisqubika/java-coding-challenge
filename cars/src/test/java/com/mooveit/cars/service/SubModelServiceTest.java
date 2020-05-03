package com.mooveit.cars.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubModelServiceTest {

	@Autowired
	private SubModelService modelService;
	
	@Test
	public void testGetModelById() {
		Object response = modelService.getModelById(10000);
		assertNotNull(response);
		assertTrue(response.getClass().getName().equals("com.mooveit.cars.dto.DefaultMessageDTO"));
	}

	@Test
	public void testGetAllModelsByBrandName() {		
		Object listModel  = modelService.getAllModelsByBrandName("kawasaki");
		assertNotNull(listModel);
		assertTrue(listModel.getClass().getName().equals("com.mooveit.cars.dto.DefaultMessageDTO"));
	}

}
