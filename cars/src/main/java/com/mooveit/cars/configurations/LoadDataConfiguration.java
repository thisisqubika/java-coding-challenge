package com.mooveit.cars.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix="load-data")
public class LoadDataConfiguration {

	private String file;
	private String brandName;	
	
}
