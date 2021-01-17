package com.mooveit.cars.ingestion;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Ingestion;
import com.mooveit.cars.domain.Specification;
import com.mooveit.cars.repositories.BrandRepository;

/**
 * This strategy takes a transient {@link Brand} and:
 * <ol>
 * <li>If the {@link Brand} doesn't exists into the repository, save it as it is
 * <li>If the {@link Brand} exists, for each {@link Specification}:
 * <li>If {@link Specification} doesn't exists, add it to the {@link Brand}
 * <li>If {@link Specification} exists, this will be replaced for the new one
 * </ol>
 */
@Service
public class MergeBrandsIngestStrategy implements IngestStrategy {

	@Autowired
	private BrandRepository brandRepo;

	/*
	 * @see com.mooveit.cars.ingestion.IngestStrategy#ingest(java.lang.String,
	 * com.mooveit.cars.domain.Brand)
	 */
	@Override
	public Ingestion ingest(String source, Brand brandToIngest) {

		Optional<Brand> persistentBrand = brandRepo.findByName(brandToIngest.getName());

		if (!persistentBrand.isPresent()) {
			// If brand didn't exists, persist complete new brand
			brandToIngest = brandRepo.save(brandToIngest);
			Long totalSpecs = brandToIngest.getSpecifications().count();
			return new Ingestion(brandToIngest, new Date(), source, totalSpecs, totalSpecs);
		} else {

			// TODO : If brand exists, persist new Specifications and update
			// existent ones.

		}
		return new Ingestion();

	}

}
