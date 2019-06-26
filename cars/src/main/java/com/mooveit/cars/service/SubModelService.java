package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.models.SubModelData;
import com.mooveit.cars.repositories.SubModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubModelService {

    @Autowired
    private SubModelRepository subModelRepository;

    public SubModel createSubModel(Model model, SubModelData subModelData, Wheel wheel, Engine engine){
        setSubModelDates(model, subModelData);
        SubModel subModel = new SubModel(subModelData.getName(), subModelData.getFrom(), subModelData.getTo(), subModelData.getLine(), model, wheel, engine);
        subModel.setModel(model);
        return subModelRepository.save(subModel);
    }

    private void setSubModelDates(Model model, SubModelData subModelData) {
        if(subModelData.getFrom() == null){
            subModelData.setFrom(model.getFrom());
        }
        if(subModelData.getTo() == null){
            subModelData.setTo(model.getTo());
        }
    }

}
