package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Engine;

public interface EngineRepositoryI extends JpaRepository<Engine, Integer> {

}
