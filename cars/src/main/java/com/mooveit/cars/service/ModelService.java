package com.mooveit.cars.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.dto.ModelDTO;
import com.mooveit.cars.repositories.ModelRepository;

@Service
public class ModelService {

	private final ModelRepository modelRepo;
	private Model model;
	
	@Autowired
	private EngineService engineService;
	
	@Autowired
	private WheelService wheelService;

	public ModelService(ModelRepository modelRepo) {
		super();
		this.modelRepo = modelRepo;
	}
	
	/**
	 * Method to get data and check for update/insert
	 * @param dto {@link ModelDTO}
	 * @param brand
	 * @return {@link Model} the found entity
	 */
	public Model processModelEntityFromDto (ModelDTO dto, Brand brand) {
		model = new Model();
		model.setFromYear(dto.getFrom());
		model.setToYear(dto.getTo());
		model.setType(dto.getType());
		model.setName(dto.getName());
		Optional<Model> opModel = modelRepo.findOne(Example.of(model));
		if (!opModel.isPresent()) {
			model.setBrand(brand);
			model.setEngine(engineService.processEngineEntityFromDto(dto.getEngine()));
			model.setWheel(wheelService.processWheelEntityFromDto(dto.getWheel()));
			model = modelRepo.saveAndFlush(model);
		} else model = opModel.get();		
		return model;
	}
}
