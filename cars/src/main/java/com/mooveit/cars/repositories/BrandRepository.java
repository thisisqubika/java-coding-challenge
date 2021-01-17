package com.mooveit.cars.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Brand;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {

	public Optional<Brand> findByName(String name);

}
