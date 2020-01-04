package com.mooveit.cars.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.mooveit.cars.domain.Model;

public interface ModelRepository extends JpaRepository<Model, Long>  {
	
	@Query(value = "SELECT M.* "
			+ "FROM MODELS AS M "
			+ "WHERE M.model_name like %:model_name%",
            nativeQuery = true
    )
    List<Model> getCarSpecificationByBrand(@Param("model_name") String model_name);

}
