package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Long> {
}
