package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Wheels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WheelsRepository extends JpaRepository<Wheels, Long> {

    Optional<Wheels> findFirstBySizeAndType(int size, Wheels.Type type);
}
