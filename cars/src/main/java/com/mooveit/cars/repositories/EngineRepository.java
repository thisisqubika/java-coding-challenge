package com.mooveit.cars.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.ENGINE;

@Repository
public interface EngineRepository extends CrudRepository <ENGINE, Integer> {

}
