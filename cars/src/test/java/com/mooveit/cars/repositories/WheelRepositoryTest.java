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

import com.mooveit.cars.domain.Wheel;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WheelRepositoryTest {

	@Autowired
	private WheelRepository wheelRepository;

	@Test
	public void testSaveAndDeleteSingleWheel() throws Exception {

		Wheel savedWheel = wheelRepository.save(new Wheel("R15", "STEEL"));

		Long entityId = savedWheel.getId();
		assertTrue(wheelRepository.existsById(entityId));
		assertTrue(wheelRepository.findById(entityId).isPresent());

		wheelRepository.deleteById(savedWheel.getId());

		assertFalse(wheelRepository.existsById(entityId));
		assertFalse(wheelRepository.findById(entityId).isPresent());

	}

	@Test
	public void testFindBySizeAndType() throws Exception {
		
		wheelRepository.save(new Wheel("R15", "STEEL"));

		Optional<Wheel> resultList = wheelRepository.findBySizeAndType("R15", "STEEL");
		assertTrue(resultList.isPresent());

	}

}
