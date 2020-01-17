package com.mooveit.cars.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mooveit.cars.domain.Wheel;

public interface WheelRepository extends JpaRepository<Wheel, Long>{
	
	@Query(value = "SELECT W.* "
			+ "FROM WHEELS AS W "
			+ "WHERE W.id = :id",
            nativeQuery = true
    )
	Optional<List<Wheel>> getById(@Param("id") Long id);

}
