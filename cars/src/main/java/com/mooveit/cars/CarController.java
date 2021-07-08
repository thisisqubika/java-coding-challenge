package com.mooveit.cars;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.domain.Car;
import com.mooveit.cars.repositories.CarRepository;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;


    @GetMapping("/cars/{id}")
    @ResponseBody
    public Optional<Car> findById(@PathVariable Long id)
    {
    	return carRepository.findById(id);
    }

//    @GetMapping("/cars/{brand}")
//    @ResponseBody
//    public Car findByBrand(@PathVariable String brand)
//    {
//        return carRepository.findByBrand(brand);
//    }



    @GetMapping("/cars")
    @ResponseBody
    public List<Car> findAll()
    {
    	return carRepository.findAll();

    }
}