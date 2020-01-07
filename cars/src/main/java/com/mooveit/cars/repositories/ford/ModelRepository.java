package com.mooveit.cars.repositories.ford;

import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.ford.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This class allows to manage {{ModelEntity}} by providing CRUD operations.
 *
 */
@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Integer> {

  /**
   * Given a name, returns a {{ModelEntity}} if it exists.
   *
   * @param name  String The name.
   * @return
   */
  Optional<ModelEntity> findByName(String name);

  /**
   * Given a {{CarBrand}}, returns all {{ModelEntity}} of that brand.
   *
   * @param brand   {{CarBrand}}  The brand.
   * @return
   */
  List<ModelEntity> findAllByBrand(CarBrand brand);
}