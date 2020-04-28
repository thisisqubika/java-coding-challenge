package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
