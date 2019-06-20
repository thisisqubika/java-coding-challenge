package com.mooveit.cars.controllers;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.services.interfaces.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private IModelService modelService;

    @RequestMapping(method = RequestMethod.GET, path = "/model")
    public ResponseEntity<Model> byId(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return modelService.findById(id).map(modelOpt ->
                ResponseEntity.of(modelOpt.toJavaOptional())
        ).getOrElseThrow(t -> new Exception("An error has occurred retrieving the car by id. Please verify the id."));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/brand")
    public ResponseEntity<List<Model>> byBrand(@RequestParam(value = "brand", defaultValue = "") String brand) throws Exception {
        return modelService.findByName(brand).map(ResponseEntity::ok)
                .getOrElseThrow(() -> new Exception("An error has occurred retrieving the car models"));
    }

}
