package com.mooveit.cars.repositories.ford;

import com.mooveit.cars.domain.ford.SubModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This class allows to manage {{SubModelEntity}} by providing CRUD operations.
 *
 */
@Repository
public interface SubModelRepository extends JpaRepository<SubModelEntity, Integer> {

  /**
   * Returns an Optional with the {{SubModelEntity}} if it exists.
   *
   * @param name  String The name of the submodel (this is unique te each submodel)
   * @return
   */
  Optional<SubModelEntity> findByName(String name);
}
