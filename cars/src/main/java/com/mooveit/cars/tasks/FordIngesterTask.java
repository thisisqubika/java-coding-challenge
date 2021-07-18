package com.mooveit.cars.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mooveit.cars.service.CarsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FordIngesterTask {

  protected Logger log = LogManager.getLogger(this.getClass());
	
  @Autowired
  private CarsService carsService;
  
  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
	  
    log.info("startnig the batch process");
    boolean success = carsService.processcars();
    log.info("ending the batch process, status is : " + success);
    
    
  }
}
