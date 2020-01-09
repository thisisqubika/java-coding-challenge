package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.CarModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Long> {

    Optional<CarModelEntity> findByIdAndIsActiveTrue(Long id);

    Optional<CarModelEntity> findByNameAndIsActiveTrue(String name);

    List<CarModelEntity> findByParentModelAndIsActiveTrue(CarModelEntity carModelEntityParent);

    List<CarModelEntity> findByBrand(String brand);

    Page<CarModelEntity> findByBrand(String brand, Pageable pageable);

}
