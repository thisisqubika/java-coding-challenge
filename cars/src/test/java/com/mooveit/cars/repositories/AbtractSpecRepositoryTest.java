package com.mooveit.cars.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.AbstractSpec;
import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.EngineType;
import com.mooveit.cars.domain.Modification;
import com.mooveit.cars.domain.Specification;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AbtractSpecRepositoryTest {

	@Autowired
	private SpecificationRepository specRepository;

	@Autowired
	private AbstractSpecRepository abstractRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	private Specification createCarSpec(boolean withModifications) {
		Brand brand = new Brand("Ford");
		brand = brandRepository.save(brand);
		
		Engine eng = new Engine(100, EngineType.HYBRID);
		Specification spec = new Specification(brand,"Specification A", 1994, 1996, "subcompact", eng, null);
		
		if (withModifications){
			spec.addModification(new Modification("Modification A.1", 1900, 1950, "high-line", null, null));
		}
		return specRepository.save(spec);
	}
	

	@Test
	public void testSaveAndDeleteSpecificationWitnModifications() throws Exception {
		Specification savedSpec  = createCarSpec(true);
		
		// Check collection was persisted using cascade strategy
		assertTrue(savedSpec.hasModifications());
		Modification savedModif = savedSpec.getModification(0);
		assertNotNull(savedModif.getId());

		Iterable<AbstractSpec> iterSpecs = abstractRepository.findAllById(Arrays.asList(savedSpec.getId(), savedModif.getId()));
		for (AbstractSpec abstractSpec : iterSpecs) {
			assertNotNull(abstractSpec.getId());
		}
		
	}


}
