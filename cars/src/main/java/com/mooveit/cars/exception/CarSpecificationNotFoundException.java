package com.mooveit.cars.exception;

public class CarSpecificationNotFoundException extends BussinesEntityNotFoundException {
    public CarSpecificationNotFoundException(Long id) {
        super("Could not found car specification " + id);
    }
}
