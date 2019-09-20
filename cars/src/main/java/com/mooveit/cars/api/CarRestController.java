package com.mooveit.cars.api;

import com.mooveit.cars.api.dtos.CarDto;
import com.mooveit.cars.api.mappers.CarDtoMapper;
import com.mooveit.cars.domain.Car;
import com.mooveit.cars.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CarRestController {

    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;

    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") long id) {
        return carRepository
                .findById(id)
                .map(carDtoMapper::carToCarDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CarDto> getAllCars(@RequestParam(name = "brand", required = false) String brand) {
        List<Car> foundCars =
                brand == null ? carRepository.findAll() : carRepository.findByBrandIgnoreCase(brand);

        return foundCars.stream().map(carDtoMapper::carToCarDto).collect(Collectors.toList());
    }
}
