package com.mooveit.cars.repositories.ford;

import com.mooveit.cars.domain.ford.EngineEntity;
import com.mooveit.cars.domain.ford.types.EngineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This class allows to manage {{EngineEntity}} by providing CRUD operations.
 *
 */
@Repository
public interface EngineRepository extends JpaRepository<EngineEntity, Integer> {

  /**
   * Returns an Optional with an Engine if it matches the power and type.
   */
  Optional<EngineEntity> findByPowerAndType(Integer power, EngineType type);
}
