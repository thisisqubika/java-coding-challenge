package com.mooveit.cars.services;

import com.mooveit.cars.domain.Catalogue;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.exception.CarSpecificationNotFoundException;
import com.mooveit.cars.exception.CatalogueNotFoundException;
import com.mooveit.cars.repositories.CatalogueRepository;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.repositories.SubModelRepository;
import com.mooveit.cars.rest.dto.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecificationService {

    @Autowired
    ModelRepository modelRepository;
    @Autowired
    SubModelRepository subModelRepository;
    @Autowired
    CatalogueRepository catalogueRepository;

    public List<CarSpecification> getSpecificationByCatalogueId(String catalogueId){
        Optional<Catalogue> optionalCatalogue = catalogueRepository.findById(catalogueId);
        if(!optionalCatalogue.isPresent()){
            throw new CatalogueNotFoundException(catalogueId);
        }
        List<Model> modelList = modelRepository.findByCatalogue(optionalCatalogue.get());
        List<SubModel> subModelList = new ArrayList<>();
        for (Model model:modelList){
            subModelList.addAll(subModelRepository.findByModel(model));
        }
        List<CarSpecification> carSpecificationList = new ArrayList<>();
        addModelsToSpecificationsList(modelList, carSpecificationList);
        addSubModelsToSpecificationList(subModelList, carSpecificationList);
        return carSpecificationList;
    }

    private void addModelsToSpecificationsList(List<Model> modelList, List<CarSpecification> carSpecificationList) {
        modelList.forEach(m-> carSpecificationList.add(new CarSpecification(m)));
    }

    private void addSubModelsToSpecificationList(List<SubModel> subModelList, List<CarSpecification> carSpecificationList) {
        subModelList.forEach(sm-> carSpecificationList.add(new CarSpecification(sm)));
    }


    public CarSpecification getSpecificationById(Long id) {
        Optional<Model> optionalModel = modelRepository.findById(id);
        if(optionalModel.isPresent()){
            return new CarSpecification(optionalModel.get());
        }
        Optional<SubModel> optionalSubModel = subModelRepository.findById(id);
        if(optionalSubModel.isPresent()){
            return new CarSpecification(optionalSubModel.get());
        }
        throw new CarSpecificationNotFoundException(id);
    }
}
