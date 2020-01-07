package com.mooveit.cars.repositories.ford;

import com.mooveit.cars.domain.ford.WheelEntity;
import com.mooveit.cars.domain.ford.types.RimSize;
import com.mooveit.cars.domain.ford.types.RimType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This class allows to manage {{WheelEntity}} by providing CRUD operations.
 *
 */
@Repository
public interface WheelRepository extends JpaRepository<WheelEntity, Integer> {

  /**
   * Returns an Optional with a {{WheelEntity}} if it matches the size and type.
   */
  Optional<WheelEntity> findBySizeAndType(RimSize size, RimType type);
}