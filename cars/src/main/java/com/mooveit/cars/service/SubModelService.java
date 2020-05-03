package com.mooveit.cars.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.dto.SubModelDTO;
import com.mooveit.cars.repositories.SubModelRepository;

@Service
public class SubModelService {

	private SubModel subModel;
	private final SubModelRepository subModelRepo;	
	
	@Autowired
	private EngineService engineService;
	@Autowired
	private WheelService wheelService;
	
	public SubModelService(SubModelRepository subModelRepo) {
		super();
		this.subModelRepo = subModelRepo;
	}

	/**
	 * Method to get data and check for update/insert
	 * @param dto {@link SubModelDTO}
	 * @param model {@link Model}
	 * @return {@link SubModel}
	 */
	public SubModel processSubModelEntityFromDto (SubModelDTO dto, Model model) {
		subModel = new SubModel();
		subModel.setLine(dto.getLine());
		subModel.setName(dto.getName());
		Optional<SubModel> opSubModel = subModelRepo.findOne(Example.of(subModel));
		if(!opSubModel.isPresent()) {
			subModel.setModel(model);
			subModel.setFromYear(dto.getFrom());
			subModel.setToYear(dto.getTo());
			subModel.setEngine(engineService.processEngineEntityFromDto(dto.getEngine()));
			subModel.setWheel(wheelService.processWheelEntityFromDto(dto.getWheel()));
			subModel = subModelRepo.saveAndFlush(subModel);
		} else subModel = opSubModel.get();
		return subModel;
	}
}
