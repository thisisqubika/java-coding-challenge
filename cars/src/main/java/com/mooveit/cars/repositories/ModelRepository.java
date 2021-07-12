package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
