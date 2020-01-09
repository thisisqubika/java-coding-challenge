package com.mooveit.cars.interfaces;

import com.mooveit.cars.domain.CarModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarModelService {
    CarModelEntity findCarModelById(Long id);
    Page<CarModelEntity> findAllCarModelByBrand(String brandName, Pageable pageable);
    Page<CarModelEntity> findAllCarModel(Pageable pageable);
}
