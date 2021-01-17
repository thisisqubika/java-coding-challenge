package com.mooveit.cars.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.EngineType;
import com.mooveit.cars.domain.Modification;
import com.mooveit.cars.domain.Specification;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpecificationRepositoryTest {

	@Autowired
	private SpecificationRepository specRepository;

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
	public void testSaveAndDeleteSingleSpec() throws Exception {
		Specification savedSpec  = createCarSpec(false);		

		Long entityId = savedSpec.getId();
		assertTrue(specRepository.existsById(entityId));
		assertTrue(specRepository.findById(entityId).isPresent());

		specRepository.deleteById(entityId);

		assertFalse(specRepository.existsById(entityId));
		assertFalse(specRepository.findById(entityId).isPresent());

	}


	@Test
	public void testSaveAndDeleteSpecificationWitnModifications() throws Exception {
		Specification savedSpec  = createCarSpec(true);
		
		// Check collection was persisted using cascade strategy
		assertTrue(savedSpec.hasModifications());
		Modification savedModif = savedSpec.getModification(0);
		assertNotNull(savedModif.getId());

		// Check all entities are removed using cascade strategy
		specRepository.deleteById(savedSpec.getId());
		assertFalse(specRepository.existsById(savedSpec.getId()));
		assertFalse(specRepository.existsById(savedModif.getId()));
	}

	@Test
	public void testFindByName() throws Exception {
		Specification savedSpec  = createCarSpec(false);

		Iterable<Specification> result = specRepository.findByName(savedSpec.getName());
		assertTrue(result.iterator().hasNext());
	}

	@Test
	public void testFindByEngineType() throws Exception {
		Specification savedSpec  = createCarSpec(false);

		Iterable<Specification> result  = specRepository.findByEngineType(savedSpec.getEngine().getType());
		assertTrue(result.iterator().hasNext());
	}

}
