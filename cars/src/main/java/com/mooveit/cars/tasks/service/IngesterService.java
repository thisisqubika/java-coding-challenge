package com.mooveit.cars.tasks.service;

import com.mooveit.cars.tasks.model.CarSpecifications;

/**
 * Handles {{CarSpecifications}} for different brands and persists them in the db.
 */
public interface IngesterService {

    /**
     * Processes the XML files for the maker and stores them for future use.
     */
    public void processAndSave(CarSpecifications carsSpecifications) throws Exception;
}
