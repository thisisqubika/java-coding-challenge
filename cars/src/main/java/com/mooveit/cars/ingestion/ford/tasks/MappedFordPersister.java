package com.mooveit.cars.ingestion.ford.tasks;

import com.mooveit.cars.domain.Car;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Wheels;
import com.mooveit.cars.repositories.CarRepository;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.WheelsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class MappedFordPersister {

    private final EngineRepository engineRepository;
    private final WheelsRepository wheelsRepository;
    private final CarRepository carRepository;

    void persist(Car mappedFordCar) {
        Car carToPersist = mappedFordCar.toBuilder().build();
        persistInternalEntities(carToPersist);
        // This save method will save by cascade all the car sub-models as well.
        carToPersist = carRepository.save(carToPersist);
        log.debug("Persisted Ford car: " + carToPersist);
        if (!carToPersist.getSubModels().isEmpty()) {
            log.debug("With sub-models: ");
            carToPersist.getSubModels().forEach(subModel -> log.debug(subModel.toString()));
        }
    }

    private void persistInternalEntities(Car carToPersist) {
        carToPersist.setEngine(persistEngine(carToPersist.getEngine()));
        carToPersist.setWheels(persistWheels(carToPersist.getWheels()));

        if (!carToPersist.getSubModels().isEmpty()) {
            carToPersist.getSubModels().forEach(subModel -> persistSubModel(carToPersist, subModel));
        }
    }

    private Engine persistEngine(Engine engine) {
        return engineRepository
                .findFirstByPowerAndType(engine.getPower(), engine.getType())
                .orElse(engineRepository.save(engine));
    }

    private Wheels persistWheels(Wheels wheels) {
        return wheelsRepository
                .findFirstBySizeAndType(wheels.getSize(), wheels.getType())
                .orElse(wheelsRepository.save(wheels));
    }

    private void persistSubModel(Car parentModel, Car subModel) {
        subModel.setParentModel(parentModel);
        if (subModel.getType() == null) {
            // The sub-model inherits the same type of the parent model.
            subModel.setType(parentModel.getType());
        }
        persistInternalEntities(subModel);
    }
}
