package com.mooveit.cars.tasks.service;

import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.ford.EngineEntity;
import com.mooveit.cars.domain.ford.ModelEntity;
import com.mooveit.cars.domain.ford.SubModelEntity;
import com.mooveit.cars.domain.ford.WheelEntity;
import com.mooveit.cars.domain.ford.types.EngineType;
import com.mooveit.cars.domain.ford.types.RimSize;
import com.mooveit.cars.domain.ford.types.RimType;
import com.mooveit.cars.repositories.ford.service.EngineService;
import com.mooveit.cars.repositories.ford.service.ModelService;
import com.mooveit.cars.repositories.ford.service.SubModelService;
import com.mooveit.cars.repositories.ford.service.WheelService;
import com.mooveit.cars.tasks.exceptions.FileReaderException;
import com.mooveit.cars.tasks.model.CarSpecifications;
import com.mooveit.cars.tasks.model.Catalogue;
import com.mooveit.cars.tasks.model.Model;
import com.mooveit.cars.tasks.model.SubModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Processes Ford's {{CarSpecifications}} and persists them in the db.
 *
 */
@Service
public class FordIngesterService implements IngesterService {

    final Logger log = LoggerFactory.getLogger(FordIngesterService.class);

    @Autowired
    private ModelService modelService;

    @Autowired
    private EngineService engineService;

    @Autowired
    private WheelService wheelService;

    @Autowired
    private SubModelService subModelService;

    @Override
    public void processAndSave(CarSpecifications carSpecifications) throws FileReaderException {
        Catalogue fordCatalogue = (Catalogue) carSpecifications;

        log.info("Starting to process Ford's catalogue.");
        fordCatalogue.getModel().stream().forEach(m -> processModel(m));
        log.info("Ford's catalogue processed and saved sucessfully.");
    }

    private void processModel(Model model) {
        ModelEntity modelEntity = modelService.saveModel(
          new ModelEntity(
            0,
            model.getName(),
            CarBrand.FORD,
            model.getFrom(),
            model.getTo(),
            model.getType(),
            getEngineEntityFromModel(model.getEngine().getPower(), model.getEngine().getType()),
            getWheelEntityFromModel(model.getWheels().getSize(), model.getWheels().getType()),
            null)
        );

        Set<SubModelEntity> subModelEntities = processSubModel(modelEntity, model.getSubModels());

        modelEntity.setSubmodels(subModelEntities);

        modelService.saveModel(modelEntity);

    }

    private Set<SubModelEntity> processSubModel(ModelEntity model, SubModel subModel) {
        return subModel.getModels().stream().map(m ->
          subModelService.saveSubModel(new SubModelEntity(
            0,
              m.getName(),
              model,
              m.getFrom(),
              m.getTo(),
              m.getLine(),
              getEngineEntityFromModel(m.getEngine().getPower(), m.getEngine().getType()),
              getWheelEntityFromModel(m.getWheels().getSize(), m.getWheels().getType())
            ))
        ).collect(Collectors.toSet());
    }

    private EngineEntity getEngineEntityFromModel(Integer power, EngineType type) {
        return engineService.saveEngine(new EngineEntity(0, power, type));
    }

    private WheelEntity getWheelEntityFromModel(RimSize size, RimType type) {
        return wheelService.saveWheel(new WheelEntity(0, size, type));
    }
}
