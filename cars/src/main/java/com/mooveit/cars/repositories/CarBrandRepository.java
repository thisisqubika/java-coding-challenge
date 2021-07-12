package com.mooveit.cars.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.CarBrand;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
	Optional<CarBrand> getCarBrandByName(String name);
}
