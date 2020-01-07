package com.mooveit.cars.repositories.ford.service;

import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.ford.ModelEntity;
import com.mooveit.cars.repositories.ford.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles persistence or info related to {{ModelEntity}}.
 *
 */
@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    /**
     * Saves a ModelEntity to the db, or updates it if a ModelEntity with the same id already exist.
     *
     * @param model
     */
    public ModelEntity saveModel(ModelEntity model) {
        Optional<ModelEntity> existingModel = modelRepository.findByName(model.getName());
        model.setId(existingModel.map(m -> m.getId()).orElse(0));

        return modelRepository.save(model);
    }

    /**
     * Returns an Optional with the [[ModelEntity]] if it exists or None if it doesn't exist.
     *
     * @param id
     * @return Optional[[ModelEntity]] The ModelEntity.
     */
    public Optional<ModelEntity> findModelById(int id) {
        return modelRepository.findById(id);
    }

    /**
     * Returns all [[ModeEntity]] for a given brand.
     *
     * @param brand String the brand.
     * @return
     */
    public List<ModelEntity> findModelsByBrand(String brand) {
        try {
            CarBrand carBrand = CarBrand.valueOf(brand.toUpperCase());
            return modelRepository.findAllByBrand(carBrand);
        } catch (IllegalArgumentException e) {
            // Case that the brand requested is not one of our car providers.
            return new ArrayList<>();
        }
    }
}
