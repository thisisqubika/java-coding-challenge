package com.mooveit.cars.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mooveit.cars.domain.Model;

public interface ModelRepositoryI extends JpaRepository<Model, Integer> {
	
	Model getByName(String name);
	
	@Query("select m from Model m, Brand b where m.brand.name = LOWER(:brandname)")
	List<Model> findByBrandName(@Param("brandname") String brandname);
	
}
