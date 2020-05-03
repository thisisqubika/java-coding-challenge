package com.mooveit.cars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.SubModel;

public interface SubModelRepository extends JpaRepository<SubModel, Integer> {

	List<SubModel> findAllByModelBrand (Brand brand);
}
