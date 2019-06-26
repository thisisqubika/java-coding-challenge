package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Wheel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WheelRepository extends CrudRepository<Wheel,Long> {

}
