package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long>
{
  // Car findByBrand(String brand);

}