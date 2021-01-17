package com.mooveit.cars.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.EngineType;

@Repository
@RestResource(exported = false)
public interface EngineRepository extends PagingAndSortingRepository<Engine, Long> {

	public Optional<Engine> findByPowerAndType(Integer power, EngineType type);

	public Optional<Engine> findByPower(Integer power);

}
