package com.mooveit.cars.configurations;

import com.mooveit.cars.exception.BussinesEntityNotFoundException;
import com.mooveit.cars.exception.CarSpecificationNotFoundException;
import com.mooveit.cars.exception.CatalogueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler({BussinesEntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String entityNotFoundHandler(BussinesEntityNotFoundException ex){
        return ex.getMessage();
    }
}
