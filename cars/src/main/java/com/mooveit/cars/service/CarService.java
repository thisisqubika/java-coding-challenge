package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.models.CatalogueData;
import com.mooveit.cars.models.ModelData;
import com.mooveit.cars.models.SubModelData;
import com.mooveit.cars.utils.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private ModelService modelService;

    @Autowired
    private WheelService wheelService;

    @Autowired
    private EngineService engineService;

    @Autowired
    private SubModelService subModelService;

    public void createDomainsFromFile(){
        CatalogueData catalogueData = FileReader.readFile();
        catalogueData.getModel().forEach(this::createCar);
    }

    private void createCar(ModelData modelData){
        Wheel wheel = wheelService.createWheel(modelData.getWheel());
        Engine engine = engineService.createEngine(modelData.getEngine());
        Model model = modelService.createModel(modelData, wheel, engine);
        List<SubModelData> subModelList = modelData.getSubModelContainer().getSubModelData();
        for (SubModelData subModelData: subModelList) {
            Wheel subModelWheel =  wheelService.createWheel(subModelData.getWheel());
            Engine subModelEngine = engineService.createEngine(subModelData.getEngine());
            subModelService.createSubModel(model, subModelData, subModelWheel, subModelEngine);
        }
    }


    public Wheel getCarModelWheels(String name){
        Model model = modelService.getModelByName(name);
        return wheelService.getWheelsById(model.getWheel().getId());
    }

    public Wheel getCarModelWheels(Integer id){
        Model model = modelService.getModelById(id);
        return wheelService.getWheelsById(model.getWheel().getId());
    }

    public Engine getCarModelEngine(String name){
        Model model = modelService.getModelByName(name);
        return engineService.getEngineById(model.getEngine().getId());
    }

    public Engine getCarModelEngine(Integer id){
        Model model = modelService.getModelById(id);
        return engineService.getEngineById(model.getEngine().getId());
    }

    public Model getCarModel(String name) {
        return modelService.getModelByName(name);
    }

    public Model getCarModel(Integer id) {
        return modelService.getModelById(id);
    }

    public Model getCarData(String name) {
        Wheel wheel = getCarModelWheels(name);
        Engine engine = getCarModelEngine(name);
        Model model = getCarModel(name);
        model.setWheel(wheel);
        model.setEngine(engine);

        return model;
    }

    public Model getCarData(Integer id) {
        Wheel wheel = getCarModelWheels(id);
        Engine engine = getCarModelEngine(id);
        Model model = getCarModel(id);
        model.setWheel(wheel);
        model.setEngine(engine);

        return model;
    }

}
