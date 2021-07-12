package com.mooveit.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Catalogue;

@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
}
