package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Wheels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WheelsRepository extends JpaRepository<Wheels, Long> {
    List<Wheels> findBySizeAndType(String size, String type);
}
