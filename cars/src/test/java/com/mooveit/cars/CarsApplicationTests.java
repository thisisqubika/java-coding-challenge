package com.mooveit.cars;

import com.mooveit.cars.domain.Car;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Wheels;
import com.mooveit.cars.repositories.CarRepository;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.WheelsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarsApplicationTests {

  @Autowired private EngineRepository engineRepository;
  @Autowired private WheelsRepository wheelsRepository;
  @Autowired private CarRepository carRepository;

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

  private <T extends AbstractPersistable<Long>> void testRepository(
      T insertedEntity, CrudRepository<T, Long> repository) {
    Iterable<T> foundEntities = repository.findAll();
    Iterator<T> fordEntityIterator = foundEntities.iterator();
    assertTrue(fordEntityIterator.hasNext());
    assertEquals(fordEntityIterator.next(), insertedEntity);
    assertFalse(fordEntityIterator.hasNext());
  }
}
