package com.mooveit.cars.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.EngineTable;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@Repository
@Transactional
public interface EngineRepository extends JpaRepository<EngineTable, Integer> {

}
