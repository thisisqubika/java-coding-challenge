package com.mooveit.cars.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.MalformedParametersException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.CarCatalogue;
import com.mooveit.cars.domain.CarModel;
import com.mooveit.cars.domain.CarSubModel;
import com.mooveit.cars.dto.CarModelDTO;
import com.mooveit.cars.repositories.CarDAO;
import com.mooveit.cars.repositories.CarSubModelDAO;
import com.mooveit.cars.utilities.CarUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarServicesImplement implements CarServices {

	@Autowired
	private CarDAO carDAO;

	@Autowired
	private CarSubModelDAO carSubModelDAO;

	@Override
	public ResponseEntity<CarModelDTO> findAllCars() {
		CarModelDTO dto = new CarModelDTO();
		try {
			List<CarModel> cars = carDAO.findAll();

			if (cars == null || cars.isEmpty()) {
				throw new NoResultException("no results are obtained");
			}
			log.trace("all cars are obtained");
			dto.setSuccess(true);
			dto.setMessage("OK");
			dto.setCars(cars);
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		} catch (NoResultException e) {
			dto.setMessage("ERROR: " + e.getMessage());
			log.error("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			dto.setMessage("ERROR: " + e.getMessage());
			log.error("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<CarModelDTO> findCarsByBrand(String brand) {
		CarModelDTO dto = new CarModelDTO();
		try {
			if (brand == null || brand.isEmpty()) {
				throw new InvalidParameterException("the input parameter is empty");
			}

			List<CarModel> cars = carDAO.findByBrand(brand);

			log.trace("all cars are obtained by brand");
			if (cars == null || cars.isEmpty()) {
				throw new NoResultException("no results are obtained");
			}
			dto.setSuccess(true);
			dto.setMessage("OK");
			dto.setCars(cars);
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		} catch (NoResultException e) {
			log.error("ERROR: " + e.getMessage());
			dto.setMessage("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		} catch (InvalidParameterException e) {
			log.error("ERROR: " + e.getMessage());
			dto.setMessage("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("ERROR: " + e.getMessage());
			dto.setMessage("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<CarModelDTO> findCarById(int id) {
		CarModelDTO dto = new CarModelDTO();
		List<CarModel> list = new ArrayList<CarModel>();
		try {
			if (id <= 0) {
				throw new InvalidParameterException("the input parameter must be greater than zero");
			}
			CarModel car = carDAO.findById(id);
			if (car == null || car.getId() == 0) {
				throw new NoResultException("no results are obtained");
			}
			log.trace("get a car by the id");
			list.add(car);
			dto.setSuccess(true);
			dto.setMessage("OK");
			dto.setCars(list);
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.OK);
		} catch (NoResultException e) {
			dto.setMessage("ERROR: " + e.getMessage());
			log.error("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		} catch (InvalidParameterException e) {
			dto.setMessage("ERROR: " + e.getMessage());
			log.error("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			dto.setMessage("ERROR: " + e.getMessage());
			log.error("ERROR: " + e.getMessage());
			return new ResponseEntity<CarModelDTO>(dto, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> createXMLCarModel(String xml) {
		try {
			if (xml == null || xml.isEmpty()) {
				throw new InvalidParameterException("the input parameter is empty");
			}
			InputStream targetStream = new ByteArrayInputStream(xml.getBytes());
			CarCatalogue catalogue = CarUtility.fromXML(targetStream);
			log.trace("car catalog is created");
			carDAO.saveAll(catalogue.getModels()).forEach(p -> {
				p.getSubModels().forEach(sub -> sub.setModel(p));
				carSubModelDAO.saveAll(p.getSubModels());
			});
			carDAO.flush();
			carSubModelDAO.flush();
			log.trace("car data is saved");
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (JAXBException je) {
			log.error("Error convert XML to Entitie", je);
			return new ResponseEntity<String>("Error convert XML to Entitie, Error: " + je.getMessage(),
					HttpStatus.NOT_FOUND);
		} catch (InvalidParameterException e) {
			log.error("ERROR: " + e.getMessage());
			return new ResponseEntity<String>("ERROR: " + e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("ERROR: " + e.getMessage());
			return new ResponseEntity<String>("ERROR: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> deleteCarById(int id) {
		try {
			if (id <= 0) {
				throw new InvalidParameterException("the input parameter must be greater than zero");
			}
			CarModel car = carDAO.findById(id);

			if (car == null) {
				throw new EmptyResultDataAccessException("no information found with id " + id, id);
			}

			if (car.getSubModels() != null) {
				for (CarSubModel sub : car.getSubModels()) {
					carSubModelDAO.deleteById(sub.getId());
				}
			}
			carDAO.deleteById(id);
			carDAO.flush();
			log.trace("the car with id " + id + " has been deleted");
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (InvalidParameterException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>("ERROR: " + e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (EmptyResultDataAccessException e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>("ERROR: " + e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<String>("ERROR: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
			
	}
}
