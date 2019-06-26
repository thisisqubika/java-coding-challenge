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

}
