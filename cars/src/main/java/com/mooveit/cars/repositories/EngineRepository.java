package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    List<Engine> findByPowerAndType(String power, String type);
}
