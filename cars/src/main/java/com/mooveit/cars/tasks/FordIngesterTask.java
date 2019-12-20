package com.mooveit.cars.tasks;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mooveit.cars.service.CarServices;
import com.mooveit.cars.utilities.CarUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class FordIngesterTask {

	@Autowired
	CarServices service;

	@Scheduled(cron = "${cars.ford.ingester.runCron}")
	public void ingestFile() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ford-example.xml");

			if (inputStream != null) {
				service.createXMLModel(CarUtility.toString(inputStream));
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		// log.warn("Not implemented yet.");
	}
}
