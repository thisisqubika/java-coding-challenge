package com.mooveit.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.dto.CatalogueDTO;
import com.mooveit.cars.dto.ModelDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoadGeneralDataService {

	private Model model;
	
	@Autowired
	private BrandService brandService;
	@Autowired
	private ModelService modelService;
	@Autowired
	private SubModelService subModelService;
	
	/**
	 * Method to process {@link CatalogueDTO} object mapped from XML File
	 * by a given brand
	 * @param catDTO {@link CatalogueDTO}
	 * @param brandName {@link String} from application file
	 */
	public void loadDataFromCatalogueDTO (CatalogueDTO catDTO, String brandName) {
		//Get the entity of the brand
		log.info("Searching entity of the brand {}",brandName);
		Brand brand = brandService.findOneBrandByName(brandName);
		//Let's insert the Models
		for(ModelDTO modelDto: catDTO.getModel()) {
			log.info("processing model {} ",modelDto.getName());
			model = modelService.processModelEntityFromDto(modelDto, brand);
			//Now check if has SubModel objects
			modelDto.getSubModels().forEach(obModel -> {
				log.info("processing subModel {}",obModel.getName());
				subModelService.processSubModelEntityFromDto(obModel, model);
			});
		}
		log.info("loadData finish...");
	}
}
