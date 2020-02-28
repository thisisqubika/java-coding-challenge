package com.mooveit.cars.exception;

public class CatalogueNotFoundException extends BussinesEntityNotFoundException {
    public CatalogueNotFoundException(String id){
        super("Could not find catalogue " + id);
    }
}
