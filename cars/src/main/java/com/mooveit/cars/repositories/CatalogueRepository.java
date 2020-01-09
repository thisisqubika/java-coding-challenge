package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.CatalogueEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatalogueRepository extends JpaRepository<CatalogueEntity,Long> {

}
