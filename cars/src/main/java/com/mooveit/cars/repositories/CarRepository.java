package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CarRepository extends JpaRepository<Car, Long> {

    Set<Car> findByParentModelId(Long parentModelId);

    void deleteByParentModelIsNull();
}
