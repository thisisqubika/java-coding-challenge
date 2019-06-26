package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubModelRepository extends CrudRepository<SubModel,Long> {

    List<SubModel> findByModel(Model model);
}
