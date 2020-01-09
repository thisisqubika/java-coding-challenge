package com.mooveit.cars.services;

import com.mooveit.cars.domain.CarModelEntity;
import com.mooveit.cars.models.CarModelModel;
import com.mooveit.cars.models.EngineModel;
import com.mooveit.cars.models.WheelModel;
import org.springframework.stereotype.Service;

@Service
public class ConvertBetweenEntitiesAndModels {

    public CarModelModel carModelEntityToModel(CarModelEntity carModelEntity) {
        CarModelModel carModelModel = new CarModelModel();
        carModelModel.setId(carModelEntity.getId());
        carModelModel.setBrand(carModelEntity.getBrand());
        carModelModel.setName(carModelEntity.getName());
        carModelModel.setParentName(carModelEntity.getParentModel() != null ? carModelEntity.getParentModel().getName() : "");
        carModelModel.setFrom(carModelEntity.getFrom());
        carModelModel.setTo(carModelEntity.getTo());
        carModelModel.setType(carModelEntity.getType());
        carModelModel.setLine(carModelEntity.getLine());
        EngineModel engineModel = new EngineModel(carModelEntity.getEngineEntity().getPower(), carModelEntity.getEngineEntity().getType());
        carModelModel.setEngine(engineModel);
        WheelModel wheelModel = new WheelModel(carModelEntity.getWheelEntity().getSize(), carModelEntity.getWheelEntity().getType());
        carModelModel.setWheel(wheelModel);
        return carModelModel;
    }

}
