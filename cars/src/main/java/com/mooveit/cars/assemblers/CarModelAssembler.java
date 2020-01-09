package com.mooveit.cars.assemblers;

import com.mooveit.cars.controllers.CarModelController;
import com.mooveit.cars.domain.CarModelEntity;
import com.mooveit.cars.models.CarModelModel;
import com.mooveit.cars.models.EngineModel;
import com.mooveit.cars.models.WheelModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarModelAssembler extends RepresentationModelAssemblerSupport<CarModelEntity, CarModelModel> {


    public CarModelAssembler() {
        super(CarModelController.class, CarModelModel.class);
    }

    @Override
    public CarModelModel toModel(CarModelEntity carModelEntity) {

        CarModelModel carModelModel = instantiateModel(carModelEntity);
        try {
            carModelModel.add(linkTo(methodOn(CarModelController.class).findCarModelById(carModelEntity.getId())).withSelfRel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        carModelModel.setId(carModelEntity.getId());
        carModelModel.setName(carModelEntity.getName());
        carModelModel.setBrand(carModelEntity.getBrand());
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
