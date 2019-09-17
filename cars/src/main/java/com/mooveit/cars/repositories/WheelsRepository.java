package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Wheels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WheelsRepository extends JpaRepository<Wheels, Long> {
}
