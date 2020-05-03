package com.mooveit.cars.controllers;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mooveit.cars.service.SubModelService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubModelControllerTest {

	private MockMvc restMvc;

	@Autowired
	private HttpMessageConverter<?>[] httpMessageConverters;

	@Autowired
	private SubModelService modelService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		SubModelController modelController = new SubModelController(modelService);
		this.restMvc = MockMvcBuilders.standaloneSetup(modelController).setMessageConverters(httpMessageConverters)
				.build();
	}

	@Test
	public void testGetCarModelById() {
		try {
			restMvc.perform(get("/cars/by_id?id=1")
			        .accept(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
			        .andDo(print());
		} catch (Exception e) {
			fail("Something go wrong calling the service ");
		}
	}

	@Test
	public void testGetCarsByBrand() {
		try {
			restMvc.perform(get("/cars/by_brand?brandName=ford")
			        .accept(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
			        .andDo(print());
		} catch (Exception e) {
			fail("Something go wrong calling the service ");
		}
	}

}
