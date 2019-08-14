package com.mooveit.cars.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.ModelTable;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@Repository
public interface CarsRepository extends CrudRepository<ModelTable, Long> {

	/**
	 * Find car models by brand
	 * 
	 * @param brand
	 * @return
	 */
	List<ModelTable> findByBrand(String brand);
}
