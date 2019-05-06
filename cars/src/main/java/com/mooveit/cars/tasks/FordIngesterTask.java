package com.mooveit.cars.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FordIngesterTask {

  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
    log.warn("Not implemented yet.");
  }
}
