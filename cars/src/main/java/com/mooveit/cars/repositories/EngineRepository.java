package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Engine;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
}
