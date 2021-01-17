package com.mooveit.cars.tasks;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mooveit.cars.ingestion.BrandBuilder;
import com.mooveit.cars.ingestion.FordBrandBuilder;

@Configuration
public class BuildersConfigurations {

	public class CollectionConfig {

		@Bean
		public List<BrandBuilder> brandBuilders() {
			return Arrays.asList(new FordBrandBuilder());
		}
	}

}
