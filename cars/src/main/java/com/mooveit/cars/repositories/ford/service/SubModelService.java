package com.mooveit.cars.repositories.ford.service;

import com.mooveit.cars.domain.ford.SubModelEntity;
import com.mooveit.cars.repositories.ford.SubModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Handles persistence of info related to {{SubModelEntity}}.
 */
@Service
public class SubModelService {

    @Autowired
    private SubModelRepository subModelRepository;

    /**
     * Saves a SubModelEntity to the db, or updates it if a ModelEntity with the same id already exist.
     *
     * @param submodel
     */
    public SubModelEntity saveSubModel(SubModelEntity submodel) {
        Optional<SubModelEntity> existingSubmodel = subModelRepository.findByName(submodel.getName());
        submodel.setId(existingSubmodel.map(s -> s.getId()).orElse(0));

        return subModelRepository.save(submodel);
    }
}
