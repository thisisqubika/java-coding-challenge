package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EngineRepository extends JpaRepository<Engine, Long> {

    Optional<Engine> findFirstByPowerAndType(int power, Engine.Type type);
}
