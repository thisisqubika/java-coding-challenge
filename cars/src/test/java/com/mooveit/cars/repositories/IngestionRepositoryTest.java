package com.mooveit.cars.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Ingestion;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IngestionRepositoryTest {

	@Autowired
	private IngestionRepository ingestionRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Test
	public void testSaveAndDeleteSingleIngestion() throws Exception {

		Brand brand = brandRepository.save(new Brand("Ford"));
		Ingestion ingestion = new Ingestion(brand, new Date(), "fileName.xml", 10L, 10L);

		Ingestion savedIng = ingestionRepository.save(ingestion);

		Long entityId = savedIng.getId();
		assertTrue(ingestionRepository.existsById(entityId));
		assertTrue(ingestionRepository.findById(entityId).isPresent());

		ingestionRepository.delete(savedIng);

		assertFalse(ingestionRepository.existsById(entityId));
		assertFalse(ingestionRepository.findById(entityId).isPresent());

	}

	@Test
	public void testFindAllByBrandName() throws Exception {

		Brand brand = brandRepository.save(new Brand("Ford"));
		Ingestion ingestion = new Ingestion(brand, new Date(), "fileName.xml", 10L, 10L);

		Ingestion savedIng = ingestionRepository.save(ingestion);

		Set<IngestionDTO> ingestionSet = ingestionRepository.findAllByBrandName("Ford");
		assertFalse(ingestionSet.isEmpty());
		assertTrue(ingestionSet.stream().map(i -> i.getSource()).collect(Collectors.toSet())
				.contains(savedIng.getSource()));

	}

}
