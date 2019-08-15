package com.mooveit.cars.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.AbstractSpec;

@Repository
public interface AbstractSpecRepository extends PagingAndSortingRepository<AbstractSpec, Long> {

}