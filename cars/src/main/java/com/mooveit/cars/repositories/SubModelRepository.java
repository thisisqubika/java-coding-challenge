package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.SubModel;

public interface SubModelRepository extends JpaRepository<SubModel, Integer> {

}
