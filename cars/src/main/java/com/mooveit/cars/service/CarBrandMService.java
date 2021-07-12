package com.mooveit.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.Catalogue;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModels;
import com.mooveit.cars.dto.CarBrandDto;
import com.mooveit.cars.dto.CatalogueDto;
import com.mooveit.cars.dto.ModelDto;
import com.mooveit.cars.exceptions.CarBrandNotFoundException;
import com.mooveit.cars.exceptions.ModelNotFoundException;
import com.mooveit.cars.helper.ConverterHelper;
import com.mooveit.cars.helper.JacksonHelper;
import com.mooveit.cars.helper.XStreamHelper;
import com.mooveit.cars.repositories.CarBrandRepository;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.repositories.SubModelsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarBrandMService implements ICarBrandService {
	private static final String DEFAULT_VENDOR = "Ford";

	private final CarBrandRepository carBrandRepository;
	private final ModelRepository modelRepository;
	private final SubModelsRepository subModelsRepository;

	@Autowired
	public CarBrandMService(CarBrandRepository carBrandRepository, ModelRepository modelRepository, SubModelsRepository subModelsRepository) {
		this.carBrandRepository = carBrandRepository;
		this.modelRepository = modelRepository;
		this.subModelsRepository = subModelsRepository;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ModelDto getModel(Long modelId) {
		log.debug("Entering 'getModel' method with modelId param -> {}", modelId);
		Model model = modelRepository.findById(modelId).orElseThrow(() -> new ModelNotFoundException(modelId));
		return ConverterHelper.toModelDto(model);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<ModelDto> getModelByBrand(String name) {
		log.debug("Entering 'getModelByBrand' method with name param -> {}", name);
		CarBrand carBrand = carBrandRepository.getCarBrandByName(name).orElseThrow(() -> new CarBrandNotFoundException(name));
		CarBrandDto carBrandDto = ConverterHelper.toCarBrandDto(carBrand);
		return Objects.requireNonNull(carBrandDto).getCatalogueDto().getModelsDtos();
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public CarBrandDto saveCarBrand(String xmlString) {
		log.debug("@@@@@ Entering 'saveCarBrand' method with xmlString param -> {}", xmlString);
		CatalogueDto catalogueDto = XStreamHelper.xmlToDto(xmlString);
		catalogueDto.getModelsDtos().forEach(dto -> log.debug("\nmodelDto ->\n{}", dto));
		Catalogue catalogue = ConverterHelper.fromCatalogueDto(catalogueDto);
		log.debug("@@@@@ catalogueDto:\n{}\n", JacksonHelper.prettyPrintString(catalogueDto));
		log.debug("@@@@@ catalogue:\n{}\n", JacksonHelper.prettyPrintString(catalogue));

		List<Model> copyCatalogueModels = new ArrayList<>(catalogue.getModels());
		catalogue.setModels(new ArrayList<>());

		//save each model entity before saving catalogue
		copyCatalogueModels.forEach(model -> {
			SubModels subModels = model.getSubModels();
			List<Model> copyModels = new ArrayList<>(subModels.getModels());
			subModels.setModels(new ArrayList<>());
			//save each model entity before saving subModel
			copyModels.forEach(model2 -> {
				model2.setSubModels(null);
				model2 = modelRepository.save(model2);
				log.debug("@@@@@ saved subModel::model:\n{}\n", JacksonHelper.prettyPrintString(model2));
				subModels.getModels().add(model2);
			});

			SubModels subModels1 = subModelsRepository.save(subModels);
			log.debug("@@@@@ saved subModel:\n{}\n", JacksonHelper.prettyPrintString(subModels1));

			model.setSubModels(subModels);

			Model model2 = modelRepository.save(model);
			log.debug("@@@@@ saved model2:\n{}\n", JacksonHelper.prettyPrintString(model2));

			catalogue.addModel(model);
		});

		CarBrand carBrand = new CarBrand(DEFAULT_VENDOR, catalogue);
		CarBrand carBrand2 = carBrandRepository.save(carBrand);
		log.debug("@@@@@ saved carBrand:\n{}\n", JacksonHelper.prettyPrintString(carBrand2));
		CarBrandDto carBrandDto = ConverterHelper.toCarBrandDto(carBrand2);
		log.debug("@@@@@ carBrand Dto:\n{}\n", JacksonHelper.prettyPrintString(carBrandDto));
		return carBrandDto;
	}

	@Override
	public boolean isCarBrandWithNameExists(String name) {
		log.debug("@@@@@ Entering 'isCarBrandWithNameExists' method with name param -> {}", name);
		return carBrandRepository.getCarBrandByName(name).isPresent();
	}

}
