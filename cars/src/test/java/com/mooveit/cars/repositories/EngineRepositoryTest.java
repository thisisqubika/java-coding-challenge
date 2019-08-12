package com.mooveit.cars.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.EngineType;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EngineRepositoryTest {

	@Autowired
	private EngineRepository engineRepository;

	@Test
	public void testSaveAndDeleteSingleEngine() throws Exception {

		Engine savedEngine = engineRepository.save( new Engine(1400, EngineType.GAS));

		Long entityId = savedEngine.getId();
		assertTrue(engineRepository.existsById(entityId));
		assertTrue(engineRepository.findById(entityId).isPresent());

		engineRepository.deleteById(savedEngine.getId());

		assertFalse(engineRepository.existsById(entityId));
		assertFalse(engineRepository.findById(entityId).isPresent());

	}

	@Test
	public void testFindBySizeAndType() throws Exception {
		
		engineRepository.save( new Engine(1400, EngineType.GAS));

		Optional<Engine> resultList = engineRepository.findByPower(1400);
		assertTrue(resultList.isPresent());

	}

}
