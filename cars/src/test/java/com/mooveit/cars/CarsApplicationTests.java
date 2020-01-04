package com.mooveit.cars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.repositories.ModelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private Job job;

	@Test
	public void findById() {

		Model model = modelRepository.getOne((long) 1);

		try {
			assertEquals(new Long(1), model.getModel_id());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void getModelCarSpecificationByBrand() {
		List<Model> model = modelRepository.getCarSpecificationByBrand("Aspire");
		try {
			assertTrue(model.get(0).getModel_name().toString().toLowerCase().contains("aspire"));
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void job() {
		assertNotNull(job);
		assertEquals("importCarsModelsJob", job.getName());
	}

}
