package com.mooveit.cars.tasks;

import com.mooveit.cars.domain.*;
import com.mooveit.cars.repositories.*;
import com.mooveit.cars.services.XmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FordIngesterTask {

  @Autowired
  XmlService xmlService;

  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
    saveData();
  }

  private void saveData(){
    try {
      xmlService.persistXmlCatalogue(xmlService.unmarshalCatalogue());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
  }
}
