package com.mooveit.cars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mooveit.cars.controllers.EnginesController;
import com.mooveit.cars.controllers.ModelsController;
import com.mooveit.cars.controllers.SubmodelsController;
import com.mooveit.cars.controllers.WheelsController;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.Submodel;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.repositories.SubmodelRepository;
import com.mooveit.cars.repositories.WheelRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CarsApplicationTests {

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private SubmodelRepository submodelRepository;

	@Autowired
	private WheelRepository wheelRepository;

	@Autowired
	private EngineRepository engineRepository;

	@Autowired
	private Job job;

	@Autowired
	private ModelsController modelsController;

	@Autowired
	private SubmodelsController submodelsController;

	@Autowired
	private WheelsController wheelsController;

	@Autowired
	private EnginesController enginesController;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Test
	public void xmlFile() {
		ClassPathResource resource = new ClassPathResource("ford-example.xml");
		try {
			assertTrue(resource.contentLength() > 0);
			assertTrue(resource.getFile().exists());
			assertTrue(resource.getFile().canRead());
		} catch (IOException e) {
			fail(e.toString());
		}
	}

	@Test
	public void job() {
		assertNotNull(job);
		assertEquals("importCarsModelsJob", job.getName());
	}

	@Test
	public void modelContexLoads() throws Exception {
		assertNotNull(modelsController);
	}

	@Test
	public void submodelContexLoads() throws Exception {
		assertNotNull(submodelsController);
	}

	@Test
	public void wheelContexLoads() throws Exception {
		assertNotNull(wheelsController);
	}

	@Test
	public void engineContexLoads() throws Exception {
		assertNotNull(enginesController);
	}

	@Test
	public void findModelById() {
		Model model = modelRepository.getOne((long) 1);
		try {
			assertEquals(new Long(1), model.getModel_id());
			assertEquals("Aspire", model.getModel_name());
			assertEquals(new Integer(1994), model.getModel_from());
			assertEquals(new Integer(1997), model.getModel_to());
			assertEquals("subcompact", model.getModel_type());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void findSubmodelById() {
		Submodel submodel = submodelRepository.getOne((long) 1);
		try {
			assertEquals(new Long(1), submodel.getSubmodel_id());
			assertEquals("Aspire 2", submodel.getSubmodel_name());
			assertEquals("hatchback", submodel.getSubmodel_line());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void findWheelById() {
		Wheel wheel = wheelRepository.getOne((long) 1);
		try {
			assertEquals(new Long(1), wheel.getId());
			assertEquals("R15", wheel.getWheel_size());
			assertEquals("STEEL", wheel.getWheel_type());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void findEngineById() {
		Engine engine = engineRepository.getOne((long) 1);
		try {
			assertEquals(new Long(1), engine.getId());
			assertEquals("1400", "" + engine.getEngine_power());
			assertEquals("GAS", engine.getEngine_type());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void findAllModels() {
		try {
			assertTrue(modelRepository.findAll().size() == 4);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void findAllSubmodels() {
		try {
			assertTrue(submodelRepository.findAll().size() == 8);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void findAllWheels() {
		try {
			assertTrue(wheelRepository.findAll().size() == 12);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void findAllEngines() {
		try {
			assertTrue(engineRepository.findAll().size() == 12);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void getModelCarSpecificationByBrand() {
		Optional<List<Model>> model = modelRepository.getCarSpecificationByBrand("ford");
		try {
			assertTrue(model.get().size() == 1);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void getSubModelCarSpecificationByBrand() {
		Optional<List<Submodel>> submodel = submodelRepository.getCarSpecificationByBrand("aspire");
		try {
			assertTrue(submodel.get().size() == 2);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testModelEndpointIsOK() throws Exception {
		this.mockMvc.perform(get("/api/v1/models")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/v1/models/brand/ford")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/v1/models/1")).andExpect(status().isOk());
	}

	@Test
	public void testSubModelEndpointIsOK() throws Exception {
		this.mockMvc.perform(get("/api/v1/submodels")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/v1/submodels/brand/aspire")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/v1/submodels/1")).andExpect(status().isOk());
	}

	@Test
	public void testWheelEndpointIsOK() throws Exception {
		this.mockMvc.perform(get("/api/v1/wheels")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/v1/wheels/1")).andExpect(status().isOk());
	}

	@Test
	public void testEngineEndpointIsOK() throws Exception {
		this.mockMvc.perform(get("/api/v1/engines")).andExpect(status().isOk());
		this.mockMvc.perform(get("/api/v1/engines/1")).andExpect(status().isOk());
	}
}
