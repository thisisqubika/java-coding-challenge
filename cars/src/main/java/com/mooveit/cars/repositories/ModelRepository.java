package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}
