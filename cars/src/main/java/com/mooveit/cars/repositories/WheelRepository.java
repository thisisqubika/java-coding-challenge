package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Wheel;

public interface WheelRepository extends JpaRepository<Wheel, Integer> {

}
