package com.mooveit.cars.ingestion;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Ingestion;

/**
 * Strategy to ingest a new {@link Brand}
 */
public interface IngestStrategy {

	Ingestion ingest(String source, Brand brandToIngest);

}