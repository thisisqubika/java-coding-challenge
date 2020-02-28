package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface SubModelRepository extends JpaRepository<SubModel, Long> {
    @Transactional
    void deleteByModel(Model model);

    List<SubModel> findByModel(Model model);
}
