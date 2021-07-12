package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.SubModels;

@Repository
public interface SubModelsRepository extends JpaRepository<SubModels, Long> {
}
