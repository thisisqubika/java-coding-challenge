package com.mooveit.cars.tasks;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.repositories.BrandRepository;
import com.mooveit.cars.utils.XmlProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class FordIngesterTask {

    public static final String FORD = "FORD";
    @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private XmlProcessor xmlProcessor;

  @Scheduled(cron = "${cars.ford.ingester.runCron}")
  public void ingestFile() {
      Brand brand = brandRepository.findByName(FORD);
      File file = new File(getClass().getClassLoader().getResource("ford-example.xml").getFile());
      if(file.exists()) xmlProcessor.processFile(file, brand);
  }
}
