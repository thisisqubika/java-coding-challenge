package com.mooveit.cars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.Wheels;
import com.mooveit.cars.repositories.BrandRepositoryI;
import com.mooveit.cars.repositories.EngineRepositoryI;
import com.mooveit.cars.repositories.ModelRepositoryI;
import com.mooveit.cars.repositories.WheelsRepositoryI;
import com.mooveit.cars.utils.DatabaseLoad;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsApplicationTests {

  @Autowired
  private BrandRepositoryI brandRepository;
  @Autowired
  private EngineRepositoryI engineRepository;
  @Autowired
  private WheelsRepositoryI wheelsRepository;
  @Autowired
  private ModelRepositoryI modelRepository;
  
  @Autowired
  private DatabaseLoad databaseLoad;
  
  @Test
  public void contextLoads() {  
  }
  	
	@Test
	public void testLoadingFord() {
		
		Resource resource = new ClassPathResource("ford-example.xml");
		
		assertNotNull(resource);
				
		File file;
		try {
			file = resource.getFile();
			databaseLoad.modelsLoadFord(file);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
	}

	@Test
	public void testDatabase() {
				
		Brand brand = brandRepository.getByName("Ford");
		if (brand == null) {			
			brand = brandRepository.saveAndFlush(new Brand("Ford"));
		}
		
		Engine engine = engineRepository.getByPowerAndType("1600", "GAS");
		if (engine == null) {
			engine = engineRepository.saveAndFlush(new Engine("1600", "GAS"));
		}
		
		Wheels wheels = wheelsRepository.getBySizeAndType("R15", "STEEL");
		if (wheels == null) {			
			wheels = wheelsRepository.saveAndFlush(new Wheels("R15", "STEEL"));
		}		
		
		Model model = modelRepository.getByName("Aspire");
		if (model == null) {			
			model = modelRepository.saveAndFlush(new Model("Aspire", brand, engine, wheels));
			assertNotNull(model);
		}
		
		Model model2 = modelRepository.getByName("Aspire 2");
		if (model2 == null) {
			model2 = new Model("Aspire 2", brand, engine, wheels);
			model2.setLine("hatchback");
			model2.setParentModel(model);
			model2 = modelRepository.saveAndFlush(model2);
			assertNotNull(model2);
		}		
		
		if (!model.getSubModels().contains(model2)) {
			
			model.getSubModels().add(model2);
			model = modelRepository.saveAndFlush(model);
			
			model2.setParentModel(model);
			model2 = modelRepository.saveAndFlush(model2);
			
		}

		assertNotNull(model);		
		assertNotNull(model2);
		
	}
	
	@Test
	public void testInsertEntities() {
		
		// Brand Insert
		
		Brand brand = brandRepository.save(new Brand("Ford"));		
		assertNotNull(brand);
		
		Brand foundedBrand = brandRepository.findById(brand.getId()).orElse(null);
		assertNotNull(foundedBrand);
		
		assertEquals(brand.getName(), foundedBrand.getName());
		
		// Engine Insert
		
		Engine engine = engineRepository.save(new Engine("1600", "GAS"));
		assertNotNull(engine);
		
		Engine foundedEngine = engineRepository.findById(engine.getId()).orElse(null);
		assertNotNull(foundedEngine);
		
		assertEquals(engine.toString(), foundedEngine.toString());
		
		// Wheels Insert
		
		Wheels wheel = wheelsRepository.save(new Wheels("R15", "STEEL"));
		assertNotNull(wheel);
		
		Wheels foundedWheels = wheelsRepository.findById(wheel.getId()).orElse(null);
		assertNotNull(foundedWheels);
		
		assertEquals(wheel.toString(), foundedWheels.toString());
		
		// Insert of a Model with their respective SubModels
		
		Engine engine2 = engineRepository.save(new Engine("1400", "GAS"));
		Model model = modelRepository.save(new Model("Aspire", brand, engine2, wheel));
		
		assertNotNull(model);
		
		Model subModel1 = new Model("Aspire 2", brand, engine, wheel);
		subModel1.setParentModel(model);
		Model savedSubModel1 = modelRepository.save(subModel1);
		
		assertNotNull(savedSubModel1);
		
		Model subModel2 = new Model("Aspire 4", brand, engine, wheel);
		subModel2.setParentModel(model);
		Model savedSubModel2 = modelRepository.save(subModel2);
		assertNotNull(savedSubModel2);
		
		Model foundedModel = modelRepository.findById(model.getId()).orElse(null);
		assertNotNull(foundedModel);
		
		assertNotNull(foundedModel.getSubModels());
		
	}
  
}
