package com.mooveit.cars.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mooveit.cars.domain.SubmodelTable;

/**
 * 
 * @author Lucas Arquiel
 *
 */
public interface CarSubmodelRepository extends CrudRepository<SubmodelTable, Long> {

	/**
	 * Find car submodel by model id
	 * 
	 * @param modelId
	 * @return
	 */
	List<SubmodelTable> findByModelId(Long modelId);

}
