package com.mooveit.cars.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.WHEELS;

@Repository
public interface WheelsRepository extends CrudRepository <WHEELS, Integer> {

}
