package com.mooveit.cars.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.EngineType;
import com.mooveit.cars.domain.Specification;

@Repository
public interface SpecificationRepository extends PagingAndSortingRepository<Specification, Long> {

	public List<Specification> findByName(@Param(value = "name") String name);

	public List<Specification> findByEngineType(EngineType name);
	
	public List<Specification> findByBrandName(@Param(value = "brand") String name);
}
