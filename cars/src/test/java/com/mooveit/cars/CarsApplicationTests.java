package com.mooveit.cars;

import com.mooveit.cars.domain.Car;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Wheels;
import com.mooveit.cars.ingestion.ford.tasks.FordIngesterTask;
import com.mooveit.cars.ingestion.ford.xml.mappers.CarMapper;
import com.mooveit.cars.ingestion.ford.xml.mappers.EngineMapper;
import com.mooveit.cars.ingestion.ford.xml.mappers.WheelsMapper;
import com.mooveit.cars.ingestion.ford.xml.model.FordEngine;
import com.mooveit.cars.ingestion.ford.xml.model.FordWheels;
import com.mooveit.cars.repositories.CarRepository;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.WheelsRepository;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import static com.mooveit.cars.ingestion.ford.tasks.FordIngesterTask.INGESTED_FILE_EXTENSION;
import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsApplicationTests {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private WheelsRepository wheelsRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private FordIngesterTask fordIngesterTask;
    @Autowired
    private EngineMapper engineMapper;
    @Autowired
    private WheelsMapper wheelsMapper;
    @Autowired
    private CarMapper carMapper;

    @After
    public void cleanUp() {
        carRepository.deleteByParentModelIsNull();
        engineRepository.deleteAll();
        wheelsRepository.deleteAll();
    }

    @Transactional
    @Test
    public void builderAndRepositoryTest() {
        // creating wheels
        Wheels testWheels = Wheels.builder().size(5).type(Wheels.Type.ALLOY).build();
        testWheels = wheelsRepository.save(testWheels);

        // creating engine
        Engine testEngine = Engine.builder().power(1500).type(Engine.Type.GAS).build();
        testEngine = engineRepository.save(testEngine);

        // creating parent Ford car
        Car fordFiesta =
                Car.builder()
                        .brand("Ford")
                        .modelName("Ford Fiesta")
                        .wheels(testWheels)
                        .engine(testEngine)
                        .productionYearFrom(Year.of(2010))
                        .productionYearTo(Year.now())
                        .type("compact")
                        .build();
        fordFiesta = carRepository.save(fordFiesta);

        // creating Ford sub models
        Car fordFiesta2017 =
                Car.builder()
                        .brand("Ford")
                        .modelName("Ford Fiesta 2017")
                        .wheels(testWheels)
                        .engine(testEngine)
                        .productionYearFrom(Year.of(2017))
                        .productionYearTo(Year.now())
                        .type("compact")
                        .line("hatchback")
                        .build();
        // Adding sub-model from the parent
        fordFiesta.addSubModel(fordFiesta2017);
        // testing cascade persist
        fordFiesta = carRepository.save(fordFiesta);
        fordFiesta2017 = fordFiesta.getSubModels().get(0);

        Car fordFiestaST =
                Car.builder()
                        .brand("Ford")
                        .modelName("Ford Fiesta ST")
                        .wheels(testWheels)
                        .engine(testEngine)
                        .productionYearFrom(Year.of(2017))
                        .productionYearTo(Year.now())
                        .type("compact")
                        .line("hatchback-sport")
                        .parentModel(fordFiesta)
                        .build();
        fordFiesta.addSubModel(fordFiestaST);
        fordFiestaST = carRepository.save(fordFiestaST);

        // testing the 3 repositories, equals and hashcode methods
        testRepository(testEngine, engineRepository);
        testRepository(testWheels, wheelsRepository);

        assertEquals(3, carRepository.count());
        Optional<Car> foundCar = carRepository.findById(fordFiesta.getId());
        assertTrue(foundCar.isPresent());
        assertEquals(fordFiesta, foundCar.get());
        // testing relationship parent model with sub-models
        Set<Car> subModelsFound = carRepository.findByParentModelId(fordFiesta.getId());
        assertEquals(2, subModelsFound.size());
        assertTrue(subModelsFound.contains(fordFiesta2017));
        assertTrue(subModelsFound.contains(fordFiestaST));

        // testing deletion of sub-models
        fordFiesta.removeSubModel(fordFiesta2017);
        carRepository.delete(fordFiesta2017);

        // testing cascade delete
        assertEquals(1, fordFiesta.getSubModels().size());
        carRepository.delete(fordFiesta);
        assertEquals(0, carRepository.count());
    }

    @Transactional
    @Test
    public void fordIngesterTaskTest() throws IOException {
        // Setting the base path to the temp folder
        Path basePath = folder.newFolder("ford").toPath();
        fordIngesterTask.setFordIngesterBasePathString(basePath.toString());
        fordIngesterTask.init();
        // Copying the for example in the temp folder
        Resource fordExampleResource = new ClassPathResource("ford-example.xml");
        Path forExampleFile = basePath.resolve(Paths.get("ford-example.xml"));
        Files.copy(fordExampleResource.getInputStream(), forExampleFile);

        fordIngesterTask.ingestFile();

        // Assertion for successful ingestion
        Files.list(basePath)
                .forEach(path -> assertTrue(path.toString().endsWith(INGESTED_FILE_EXTENSION)));

        // Assertion for successful persisting of Car entities
        assertEquals(12, carRepository.count());
    }

    @Test
    public void mappersTest() {
        FordEngine fordEngine = new FordEngine();
        fordEngine.setPower(1300);
        fordEngine.setType("GAS");

        Engine engine = engineMapper.fordEngineToEngine(fordEngine);
        assertEquals(1300, engine.getPower());
        assertEquals("GAS", engine.getType().name());

        FordWheels fordWheels = new FordWheels();
        fordWheels.setSize("R15");
        fordWheels.setType("STEEL");

        Wheels wheels = wheelsMapper.fordWheelsToWheels(fordWheels);
        assertEquals("R15", "R" + wheels.getSize());
        assertEquals("STEEL", wheels.getType().name());
    }

    private <T extends AbstractPersistable<Long>> void testRepository(
            T insertedEntity, CrudRepository<T, Long> repository) {
        Iterable<T> foundEntities = repository.findAll();
        Iterator<T> fordEntityIterator = foundEntities.iterator();
        assertTrue(fordEntityIterator.hasNext());
        assertEquals(fordEntityIterator.next(), insertedEntity);
        assertFalse(fordEntityIterator.hasNext());
    }
}
