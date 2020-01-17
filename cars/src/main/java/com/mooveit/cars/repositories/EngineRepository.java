package com.mooveit.cars.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mooveit.cars.domain.Engine;

public interface EngineRepository extends JpaRepository<Engine, Long>{
	
	@Query(value = "SELECT W.* "
			+ "FROM ENGINES AS W "
			+ "WHERE W.id = :id",
            nativeQuery = true
    )
	Optional<List<Engine>> getById(@Param("id") Long id);

}
