package com.mooveit.cars.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooveit.cars.domain.WheelsTable;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@Repository
@Transactional
public interface WheelsRepository extends JpaRepository<WheelsTable, Integer> {

}
