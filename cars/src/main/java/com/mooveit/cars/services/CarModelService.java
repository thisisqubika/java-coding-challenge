package com.mooveit.cars.services;

import com.mooveit.cars.interfaces.ICarModelService;
import com.mooveit.cars.domain.CarModelEntity;
import com.mooveit.cars.exceptions.EntityNotFoundException;
import com.mooveit.cars.repositories.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarModelService implements ICarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public Page<CarModelEntity> findAllCarModel(Pageable pageable) {
        return carModelRepository.findAll(pageable);
    }


    @Override
    public CarModelEntity findCarModelById(Long id) {
        Optional<CarModelEntity> optionalCarModel = carModelRepository.findByIdAndIsActiveTrue(id);
        if (optionalCarModel.isPresent()) {
            return optionalCarModel.get();
        } else {
            throw new EntityNotFoundException(" carModel no found by id:" + id);
        }
    }

    @Override
    public Page<CarModelEntity> findAllCarModelByBrand(String brandName, Pageable pageable) {
        return carModelRepository.findByBrand(brandName, pageable);
    }

}
