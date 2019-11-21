package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Wheels;

public interface WheelsRepositoryI extends JpaRepository<Wheels, Integer> {

}
