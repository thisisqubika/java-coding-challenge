package com.mooveit.cars.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mooveit.cars.catalogue.Catalogue;
import com.mooveit.cars.service.CatalogueService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@Slf4j
@Service
public class FordIngesterTask {

	private static final Logger LOG = LoggerFactory.getLogger(FordIngesterTask.class);

	private static String fileName = "Catalogues/Ford.xml";

	@Autowired
	private CatalogueService catalogueService;

	/**
	 * Read xml file with cars models and save data into the database
	 */
	@Scheduled(cron = "${cars.ford.ingester.runCron}")
	public void ingestFile() {

		LOG.info("Running ingestFile...");

		final Catalogue catalogue = catalogueService.readFile(fileName);

		catalogueService.insertCatalogue(catalogue);
	}

}
