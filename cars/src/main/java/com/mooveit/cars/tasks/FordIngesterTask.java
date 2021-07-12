package com.mooveit.cars.tasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.Catalogue;
import com.mooveit.cars.domain.SubModels;
import com.mooveit.cars.dto.CatalogueDto;
import com.mooveit.cars.exceptions.CarBrandAlreadyExistsException;
import com.mooveit.cars.helper.ConverterHelper;
import com.mooveit.cars.helper.XStreamHelper;
import com.mooveit.cars.repositories.CarBrandRepository;
import com.mooveit.cars.repositories.CatalogueRepository;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.repositories.SubModelsRepository;
import com.mooveit.cars.repositories.WheelsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FordIngesterTask {

	private final CarBrandRepository carBrandRepository;
	private final CatalogueRepository catalogueRepository;
	private final ModelRepository modelRepository;
	private final EngineRepository engineRepository;
	private final WheelsRepository wheelsRepository;
	private final SubModelsRepository subModelsRepository;

	private static final String DEFAULT_VENDOR = "Ford";

	@Autowired
	public FordIngesterTask(CarBrandRepository carBrandRepository, CatalogueRepository catalogueRepository, ModelRepository modelRepository,
			EngineRepository engineRepository, WheelsRepository wheelsRepository, SubModelsRepository subModelsRepository) {
		this.carBrandRepository = carBrandRepository;
		this.catalogueRepository = catalogueRepository;
		this.modelRepository = modelRepository;
		this.engineRepository = engineRepository;
		this.wheelsRepository = wheelsRepository;
		this.subModelsRepository = subModelsRepository;
	}

	@Scheduled(cron = "${cars.ford.ingester.runCron}")
	public void ingestFile() {
		log.debug("Ingesting XML file started!");
		Optional<CarBrand> optionalCarBrand = carBrandRepository.getCarBrandByName(DEFAULT_VENDOR);
		if(optionalCarBrand.isPresent()) {
			log.error("Car brand already exists in the database!");
			throw new CarBrandAlreadyExistsException(DEFAULT_VENDOR);
		}
		String content = getFileContent();
		log.debug("\ncontent ->\n{}", content);
		CatalogueDto catalogueDto = XStreamHelper.xmlToDto(content);

		catalogueDto.getModelsDtos().forEach(dto -> log.debug("\nmodelDto ->\n{}", dto));

		Catalogue catalogue = ConverterHelper.fromCatalogueDto(catalogueDto);

		//save each model entity before saving catalogue
		catalogue.getModels().forEach(model -> {
			log.debug("saving catalogue::engine...");
			engineRepository.save(model.getEngine());
			log.debug("saving catalogue::wheels...");
			wheelsRepository.save(model.getWheels());
			SubModels subModels = model.getSubModels();

			//save each model entity before saving subModel
			subModels.getModels().forEach(model2 -> {
				log.debug("saving saving subModel::engine...");
				engineRepository.save(model2.getEngine());
				log.debug("saving saving subModel::wheels...");
				wheelsRepository.save(model2.getWheels());
				log.debug("saving saving subModel::model...");
				model2 = modelRepository.save(model2);
				log.debug("saving subModel::model with ID -> {}", model2.getId());
			});
			log.debug("saving subModels...");
			subModelsRepository.save(subModels);
			log.debug("saving model...");
			model = modelRepository.save(model);
			log.debug("\nsaved model ->\n{}", model);
		});

		log.debug("saving catalogue...");
		catalogueRepository.save(catalogue);

        CarBrand carBrand = new CarBrand(DEFAULT_VENDOR, catalogue);

		log.debug("\nsaving car brand!\n");
		carBrand = carBrandRepository.save(carBrand);

		log.debug("\ncarBrand Dto->\n{}", ConverterHelper.toCarBrandDto(carBrand));
		log.debug("\nIngesting XML file done!");
	}

	private String getFileContent() {
		try {
			Resource resource = new ClassPathResource("ford-example.xml");
			return new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error("IOException", e);
		}
		return "";
	}
}
