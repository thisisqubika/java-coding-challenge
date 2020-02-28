package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueRepository extends JpaRepository<Catalogue, String> {
}
