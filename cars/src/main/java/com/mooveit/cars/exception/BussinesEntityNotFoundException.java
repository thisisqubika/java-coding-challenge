package com.mooveit.cars.exception;

public class BussinesEntityNotFoundException extends RuntimeException {
    public BussinesEntityNotFoundException(String mess){
        super(mess);
    }
}
