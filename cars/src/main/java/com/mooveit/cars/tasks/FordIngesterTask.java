package com.mooveit.cars.tasks;

import com.mooveit.cars.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FordIngesterTask {

  private static Logger logger = LoggerFactory.getLogger(FordIngesterTask.class);

  @Autowired
  private CarService carService;

  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
    logger.info("Scheduled task read file starting");
    carService.createDomainsFromFile();
    logger.info("Scheduled task finished");
  }
}
