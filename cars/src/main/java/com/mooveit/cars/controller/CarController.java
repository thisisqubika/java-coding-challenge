package com.mooveit.cars.controller;

import com.mooveit.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/ping")
    public ResponseEntity test(){
        return ResponseEntity.ok("Server is up.");
    }


    @GetMapping("/model/name/{name}")
    public ResponseEntity getModelSpecsByName(@PathVariable String name){

        return ResponseEntity.ok(carService.getCarData(name));
    }

    @GetMapping("/model/id/{id}")
    public ResponseEntity getModelSpecsById(@PathVariable Integer id){

        return ResponseEntity.ok(carService.getCarData(id));
    }
}
