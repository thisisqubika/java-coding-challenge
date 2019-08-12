package com.mooveit.cars.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.Wheel;

@Repository
@RestResource(exported = false)
public interface WheelRepository extends PagingAndSortingRepository<Wheel, Long> {

    public Optional<Wheel> findBySizeAndType(String R15, String type);
    
}
