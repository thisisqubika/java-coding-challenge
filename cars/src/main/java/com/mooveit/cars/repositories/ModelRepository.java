package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model,Long> {

    List<Model> findByName(String name);
}
