package com.mooveit.cars.tasks;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Catalogue;
import com.mooveit.cars.repositories.CarRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FordIngesterTask {
	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private CarRepository carRepository;	

	@Scheduled(cron = "${cars.ford.ingester.runCron}")
	public void ingestFile() throws IOException {
		System.out.println("*********Ingesting File****************");
		Resource resource=resourceLoader.getResource("classpath:ford-example.xml");
		File xmlFile = resource.getFile();
		JAXBContext jaxbContext;
		try
		{
			jaxbContext = JAXBContext.newInstance(Catalogue.class);              

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			Catalogue catalogue = (Catalogue) jaxbUnmarshaller.unmarshal(xmlFile);
			System.out.println("***Printing Catalogue models count******" + catalogue.getModelsList().size());
			System.out.println(catalogue.getModelsList().get(0).toString());
			carRepository.saveAll(catalogue.getModelsList());
			System.out.println("**********Print inserted data*********" + carRepository.findAll());
		}
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}


	}
}
