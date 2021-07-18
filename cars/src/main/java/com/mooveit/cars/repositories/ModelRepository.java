package com.mooveit.cars.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mooveit.cars.domain.MODEL;

@Repository
public interface ModelRepository extends CrudRepository <MODEL, Integer> {
	
	List<MODEL> findByName(String brand);

}
