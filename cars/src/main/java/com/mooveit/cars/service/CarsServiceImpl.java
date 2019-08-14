package com.mooveit.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.ModelTable;
import com.mooveit.cars.domain.SubmodelTable;
import com.mooveit.cars.dto.EngineDTO;
import com.mooveit.cars.dto.ModelDTO;
import com.mooveit.cars.dto.ModelListDTO;
import com.mooveit.cars.dto.SubmodelDTO;
import com.mooveit.cars.dto.SubmodelsDTO;
import com.mooveit.cars.dto.WheelsDTO;
import com.mooveit.cars.repositories.CarSubmodelRepository;
import com.mooveit.cars.repositories.CarsRepository;

/**
 * Service methods for RESTful API
 * 
 * @author Lucas Arquiel
 *
 */
@Service
public class CarsServiceImpl implements CarsService {

	@Autowired
	private CarsRepository carsRepository;

	@Autowired
	private CarSubmodelRepository submodelsRepository;

	/**
	 * Get all car models
	 * 
	 * @return
	 */
	public List<ModelTable> getModels() {
		return (List<ModelTable>) carsRepository.findAll();
	}

	/**
	 * Get car model by id
	 * 
	 * @param id
	 * @return
	 */
	public ModelDTO getModel(final Long id) {
		final Optional<ModelTable> modelTable = carsRepository.findById(id);

		if (modelTable.isPresent()) {
			return convertToModelResponse(modelTable.get());
		} else {
			return new ModelDTO();
		}
	}

	/**
	 * Get car model by brand
	 * 
	 * @param brand
	 * @return
	 */
	public ModelListDTO getModelByBrand(final String brand) {
		final List<ModelTable> modelTableList = carsRepository.findByBrand(brand);
		final ModelListDTO response = new ModelListDTO();
		final List<ModelDTO> modelResponseList = new ArrayList<ModelDTO>();
		if (modelTableList != null) {
			for (final ModelTable modelTable : modelTableList) {
				modelResponseList.add(convertToModelResponse(modelTable));
			}
		}
		response.setModelResponseList(modelResponseList);
		return response;
	}

	/**
	 * Converts Model table entity to Model Response
	 * 
	 * @param modelTable
	 * @return
	 */
	private ModelDTO convertToModelResponse(final ModelTable modelTable) {
		final ModelDTO response = new ModelDTO();

		final SubmodelsDTO submodels = new SubmodelsDTO();

		final List<SubmodelTable> submodelsTable = submodelsRepository.findByModelId(modelTable.getId());

		if (submodelsTable != null) {
			final List<SubmodelDTO> submodelsList = new ArrayList<SubmodelDTO>();
			for (final SubmodelTable submodelTable : submodelsTable) {
				final SubmodelDTO submodelResponse = new SubmodelDTO();
				submodelResponse.setId(submodelTable.getId());
				submodelResponse.setName(modelTable.getName());
				submodelResponse.setLine(submodelTable.getLine());
				submodelResponse.setYear_from(submodelTable.getYear_from());
				submodelResponse.setYear_to(submodelTable.getYear_to());
				final EngineDTO submodelEngineResponse = new EngineDTO();
				if (submodelTable.getEngine() != null) {
					submodelEngineResponse.setId(submodelTable.getEngine().getId());
					submodelEngineResponse.setPower(submodelTable.getEngine().getPower());
					submodelEngineResponse.setType(submodelTable.getEngine().getType());
				}
				submodelResponse.setEngine(submodelEngineResponse);
				final WheelsDTO submodelWheelsResponse = new WheelsDTO();
				if (submodelTable.getWheels() != null) {
					submodelWheelsResponse.setId(submodelTable.getWheels().getId());
					submodelWheelsResponse.setSize(submodelTable.getWheels().getSize());
					submodelWheelsResponse.setType(submodelTable.getWheels().getType());
				}
				submodelResponse.setWheels(submodelWheelsResponse);

				submodelsList.add(submodelResponse);
			}
			submodels.setSubmodel(submodelsList);
		}
		response.setSubmodels(submodels);
		final EngineDTO engineResponse = new EngineDTO();
		if (modelTable.getEngine() != null) {
			engineResponse.setId(modelTable.getEngine().getId());
			engineResponse.setPower(modelTable.getEngine().getPower());
			engineResponse.setType(modelTable.getEngine().getType());
		}
		response.setEngine(engineResponse);
		final WheelsDTO wheelsResponse = new WheelsDTO();
		if (modelTable.getWheels() != null) {
			wheelsResponse.setId(modelTable.getWheels().getId());
			wheelsResponse.setSize(modelTable.getWheels().getSize());
			wheelsResponse.setType(modelTable.getWheels().getType());
		}
		response.setWheels(wheelsResponse);

		response.setBrand(modelTable.getBrand());
		response.setId(modelTable.getId());
		response.setName(modelTable.getName());
		response.setType(modelTable.getType());
		response.setYear_from(modelTable.getYear_from());
		response.setYear_to(modelTable.getYear_to());

		return response;
	}

}
