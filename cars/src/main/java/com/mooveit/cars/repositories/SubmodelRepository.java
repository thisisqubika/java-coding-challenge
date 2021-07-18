package com.mooveit.cars.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.SUBMODEL;

@Repository
public interface SubmodelRepository extends CrudRepository <SUBMODEL, Integer> {

}
