package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.errors.ModelNotFoundException;
import com.mooveit.cars.models.ModelData;
import com.mooveit.cars.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.NoSuchElementException;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public Model createModel(ModelData modelData, Wheel wheel, Engine engine){
        if(modelData.getTo() == null){
            modelData.setTo(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        }
        Model model = new Model(modelData.getName(),modelData.getFrom(),modelData.getTo(),modelData.getType(), wheel, engine);
        return modelRepository.save(model);
    }

    public Model getModelByName(String name) {
        try{
            return modelRepository.findByName(name).get(0);
        }catch (IndexOutOfBoundsException e){
            throw new ModelNotFoundException("Model was not found");
        }
    }

    public Model getModelById(Integer id) {
        try{
            return modelRepository.findById(Long.valueOf(id)).get();
        }catch (NoSuchElementException e){
            throw new ModelNotFoundException("Model was not found");
        }
    }
}
