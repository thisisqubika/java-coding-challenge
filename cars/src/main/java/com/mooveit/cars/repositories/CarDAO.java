package com.mooveit.cars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mooveit.cars.domain.CarModel;

public interface CarDAO extends JpaRepository<CarModel, Integer> {

	public List<CarModel> findAll();

	public CarModel findById(int id);

	public List<CarModel> findByBrand(String brand);

	public CarModel save(CarModel car);

	public void deleteById(String id);
}
