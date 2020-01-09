package com.mooveit.cars;

import com.mooveit.cars.shedules.SchedulerTaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableJpaAuditing
public class CarsApplication implements CommandLineRunner {

    @Autowired
    private SchedulerTaskServices schedulerTaskServices;

    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        schedulerTaskServices.initTask();
    }

}
