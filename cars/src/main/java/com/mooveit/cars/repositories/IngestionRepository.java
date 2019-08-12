package com.mooveit.cars.repositories;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Ingestion;

@Repository
public interface IngestionRepository extends CrudRepository<Ingestion, Long> {

    public Optional<Ingestion> findBySourceAndDate(String source, Date date);
    
    public Set<IngestionDTO> findAllByBrandName(String brandName);
    
}
