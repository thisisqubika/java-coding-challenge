package com.mooveit.cars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.dto.DefaultMessageDTO;
import com.mooveit.cars.dto.SubModelDTO;
import com.mooveit.cars.repositories.SubModelRepository;

@Service
public class SubModelService {

	private SubModel subModel;
	private DefaultMessageDTO dmDTO;
	private Optional<SubModel> opSubModel;
	private final SubModelRepository subModelRepo;	
	
	@Autowired
	private EngineService engineService;
	@Autowired
	private WheelService wheelService;
	@Autowired
	private BrandService brandService;
	
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
		opSubModel = subModelRepo.findOne(Example.of(subModel));
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
	
	/**
	 * Method to consult a car model by id
	 * @param id
	 * @return {@link DefaultMessageDTO} if does not exit or the entity founded
	 */
	public Object getModelById (Integer id) {
		opSubModel = subModelRepo.findById(id);
		if (opSubModel.isPresent()) {
			return opSubModel.get();
		} else {
			dmDTO = new DefaultMessageDTO("The car with id "+id+" does not exist",false);
			return dmDTO;
		}
	}
	
	/**
	 * Method to get all the cars by the brand name
	 * @param brandName
	 * @return {@link DefaultMessageDTO} if does not exit or the list entity
	 */
	public Object getAllModelsByBrandName (String brandName) {
		Brand brand = brandService.findOneBrandByName(brandName);
		if (brand.getIdBrand() != null) {
			List<SubModel> listModel = subModelRepo.findAllByModelBrand(brand);
			if (listModel.isEmpty()) {
				dmDTO = new DefaultMessageDTO("The cars for the brand "+brandName+" does not exist",false);
				return dmDTO;
			}
			return listModel;
		} else {
			dmDTO = new DefaultMessageDTO("The brand "+brandName+" was not identify",false);
			return dmDTO;
		}
	}
}
