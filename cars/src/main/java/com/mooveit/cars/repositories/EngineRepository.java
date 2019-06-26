package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Engine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends CrudRepository<Engine,Long> {
}
