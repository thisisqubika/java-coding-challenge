package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

  List<Car> findByBrandIgnoreCase(String brand);

  List<Car> findByParentModelId(Long parentModelId);

  void deleteByParentModelIsNull();
}
