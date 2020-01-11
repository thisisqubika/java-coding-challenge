package com.mooveit.cars.tasks;

import lombok.extern.slf4j.Slf4j;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mooveit.cars.utils.DatabaseLoad;

@Slf4j
@Service
@Transactional
public class FordIngesterTask {
	
	@Autowired
	private DatabaseLoad databaseLoad;

  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
  	
  	log.debug("ingestFordFile");
  	Resource resource = new ClassPathResource("ford-example.xml");
		
		File file;
		try {
			file = resource.getFile();
			databaseLoad.modelsLoadFord(file);			
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
  }
  
}
