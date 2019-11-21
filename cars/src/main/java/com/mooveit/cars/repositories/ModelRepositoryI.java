package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Model;

public interface ModelRepositoryI extends JpaRepository<Model, Integer> {

}
