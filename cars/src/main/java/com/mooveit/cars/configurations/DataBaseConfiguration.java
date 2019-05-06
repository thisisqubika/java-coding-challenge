package com.mooveit.cars.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.mooveit.cars.domain"})
@EnableJpaRepositories(basePackages = {"com.mooveit.cars.repositories"})
public class DataBaseConfiguration {
}
