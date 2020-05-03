package com.mooveit.cars.tasks;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mooveit.cars.configurations.LoadDataConfiguration;
import com.mooveit.cars.service.LoadGeneralDataService;
import com.mooveit.cars.utils.JacksonXmlDataConvert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FordIngesterTask {

	@Autowired
	private LoadGeneralDataService loadGDService;

	@Autowired
	private LoadDataConfiguration ldConfig;

	@Scheduled(cron = "${cars.ford.ingester.runCron}")
	public void ingestFile() {
		log.debug("Starting process ingestFile...");
		try {
			loadGDService.loadDataFromCatalogueDTO(
					JacksonXmlDataConvert.getInstance().getFordDataXml(ldConfig.getFile()), ldConfig.getBrandName());
		} catch (IOException e) {
			log.error("Error reading file ->", e);
		}
		log.debug("Task ingestFile finish");
	}
}
