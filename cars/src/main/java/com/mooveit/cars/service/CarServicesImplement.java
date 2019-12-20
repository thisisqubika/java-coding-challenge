package com.mooveit.cars.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.CarCatalogue;
import com.mooveit.cars.domain.CarModel;
import com.mooveit.cars.repositories.CarDAO;
import com.mooveit.cars.repositories.CarSubModelDAO;
import com.mooveit.cars.utilities.CarUtility;

@Service
public class CarServicesImplement implements CarServices {

	@Autowired
	private CarDAO carDAO;

	@Autowired
	private CarSubModelDAO carSubModelDAO;

	@Override
	public List<CarModel> findAll() {
		List<CarModel> listCars = carDAO.findAll();
		return listCars;
	}

	@Override
	public List<CarModel> findByBrand(String brand) {
		List<CarModel> listCars = carDAO.findByBrand(brand);
		return listCars;
	}

	@Override
	public CarModel findById(int id) {
		CarModel car = carDAO.findById(id);
		return car;
	}

	@Override
	public void createXMLModel(String xml) {
		try {
			InputStream targetStream = new ByteArrayInputStream(xml.getBytes());
			CarCatalogue catalogue = CarUtility.fromXML(targetStream);
			carDAO.saveAll(catalogue.getModels()).forEach(p -> {
				p.getSubModels().forEach(sub -> sub.setModel(p));
				carSubModelDAO.saveAll(p.getSubModels());
			});
			carDAO.flush();
			carSubModelDAO.flush();
		} catch (Exception je) {
			System.out.println("Error");
		}

	}

}
