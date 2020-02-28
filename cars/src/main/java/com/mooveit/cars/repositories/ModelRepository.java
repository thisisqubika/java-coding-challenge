package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Catalogue;
import com.mooveit.cars.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    @Transactional
    List<Model> deleteByCatalogue(Catalogue catalogue);

    List<Model> findByCatalogue(Catalogue catalogue);
}
