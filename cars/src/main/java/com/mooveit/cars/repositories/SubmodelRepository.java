package com.mooveit.cars.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mooveit.cars.domain.Submodel;

public interface SubmodelRepository extends JpaRepository<Submodel, Long>{
	
	@Query(value = "SELECT S.* "
			+ "FROM SUBMODELS AS S "
			+ "WHERE S.submodel_name like %:name%",
            nativeQuery = true
    )
    List<Submodel> getCarSpecificationByBrand(@Param("name") String name);

}
