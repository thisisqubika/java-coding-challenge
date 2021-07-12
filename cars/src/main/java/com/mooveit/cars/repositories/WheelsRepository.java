package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Wheels;

@Repository
public interface WheelsRepository extends JpaRepository<Wheels, Long> {
}
