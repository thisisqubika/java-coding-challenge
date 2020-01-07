package com.mooveit.cars.tasks;

import com.mooveit.cars.tasks.service.FordIngesterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Scheduled Task to ingest Ford's files.
 */
@Service
public class FordIngesterTask extends IngesterTask {

  final Logger log = LoggerFactory.getLogger(FordIngesterTask.class);

  @Value("${cars.ford.ingester.path}")
  private String fordPath;

  @Autowired
  private FordIngesterService fordIngesterService;

  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
    log.info("Ford Ingester triggered.");
    super.ingestFile(fordPath, fordIngesterService);
    log.info("Ford Ingester finished.");
  }
}
