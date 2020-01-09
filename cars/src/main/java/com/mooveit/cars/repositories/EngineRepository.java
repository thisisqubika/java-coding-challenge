package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.EngineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EngineRepository extends JpaRepository<EngineEntity, Long> {
    Optional<EngineEntity> findByPowerAndTypeAndIsActiveTrue(Integer power, String type);
}
