package com.mooveit.cars.tasks;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Ingestion;
import com.mooveit.cars.ingestion.BrandBuilder;
import com.mooveit.cars.ingestion.IngestStrategy;
import com.mooveit.cars.repositories.IngestionDTO;
import com.mooveit.cars.repositories.IngestionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngesterTask {

	private static final Logger log = LoggerFactory.getLogger(IngesterTask.class);

	@Autowired
	private IngestStrategy strategy;

	@Autowired
	private IngestionRepository ingestionRepo;

	@Autowired
	private List<BrandBuilder> brandBuilders;

	public IngesterTask() {
		super();
	}

	@Scheduled(cron = "${cars.ford.ingester.runCron}")
	public void ingestBrands() {

		for (BrandBuilder builder : brandBuilders) {

			String brandName = builder.getBrandName();
			log.debug("Retrieving already imported sources for brand %s", brandName);
			Set<IngestionDTO> ingestions = ingestionRepo.findAllByBrandName(brandName);
			Set<String> sourcesToOmit = ingestions.stream().map(idto -> idto.getSource()).collect(Collectors.toSet());

			if (log.isDebugEnabled()) {
				String omitedSources = sourcesToOmit.stream().collect(Collectors.joining(", "));
				log.debug("Creating all non imported catalogs, excluding [%d] ", omitedSources);
			}

			log.debug("Creating brands for %s ", brandName);
			Map<String, Brand> brands = builder.createBrands(sourcesToOmit);
			for (String source : brands.keySet()) {

				log.debug("Ingesting %s's brand from source %s", brandName, source);
				Ingestion ingestion = strategy.ingest(source, brands.get(source));
				ingestionRepo.save(ingestion);
			}
		}

	}

	public IngestStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IngestStrategy strategy) {
		this.strategy = strategy;
	}

	public IngestionRepository getIngestionRepo() {
		return ingestionRepo;
	}

	public void setIngestionRepo(IngestionRepository ingestionRepo) {
		this.ingestionRepo = ingestionRepo;
	}

	public List<BrandBuilder> getBrandBuilders() {
		return brandBuilders;
	}

	public void setBrandBuilders(List<BrandBuilder> brandBuilders) {
		this.brandBuilders = brandBuilders;
	}

}
