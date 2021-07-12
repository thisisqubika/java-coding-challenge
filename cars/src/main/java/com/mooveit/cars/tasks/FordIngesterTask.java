package com.mooveit.cars.tasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.mooveit.cars.exceptions.CarBrandAlreadyExistsException;
import com.mooveit.cars.service.ICarBrandService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FordIngesterTask {
	private static final String DEFAULT_VENDOR = "Ford";
	private final ICarBrandService carBrandService;

	@Autowired
	public FordIngesterTask(ICarBrandService carBrandService) {
		this.carBrandService = carBrandService;
	}

	@Scheduled(cron = "${cars.ford.ingester.runCron}")
	public void ingestFile() {
		log.debug("Ingesting XML file started!");
		if(carBrandService.isCarBrandWithNameExists(DEFAULT_VENDOR)) {
			log.error("Car brand already exists in the database!");
			throw new CarBrandAlreadyExistsException(DEFAULT_VENDOR);
		}
		String xmlString = getFileContent();
		log.debug("\nxmlString ->\n{}", xmlString);
		carBrandService.saveCarBrand(xmlString);
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
