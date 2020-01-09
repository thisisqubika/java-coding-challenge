package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.WheelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WheelRepository extends JpaRepository<WheelEntity,Long> {
    Optional<WheelEntity> findBySizeAndTypeAndIsActiveTrue(String size, String type);

}
