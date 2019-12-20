package com.mooveit.cars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.CarSubModel;

public interface CarSubModelDAO extends JpaRepository<CarSubModel, Integer> {

	public List<CarSubModel> findAll();

	public CarSubModel findById(int id);

	public CarSubModel save(CarSubModel subModel);

	public void deleteById(int id);
}
