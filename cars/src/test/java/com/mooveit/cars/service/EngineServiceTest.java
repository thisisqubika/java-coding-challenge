package com.mooveit.cars.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.dto.EngineDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EngineServiceTest {

	@Autowired
	private EngineService engineService;
	private EngineDTO engineDTO;
	
	@Test
	public void testProcessEngineEntityFromDto() {
		engineDTO = new EngineDTO();
		engineDTO.setPower(1000);
		engineDTO.setType("GAS");
		Engine engine = engineService.processEngineEntityFromDto(engineDTO);
		assertNotNull(engine);
		assertEquals(engineDTO.getType(), engine.getType());
	}

}
