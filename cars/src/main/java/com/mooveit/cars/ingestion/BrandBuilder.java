package com.mooveit.cars.ingestion;

import java.util.Map;
import java.util.Set;

import com.mooveit.cars.domain.Brand;

public interface BrandBuilder {

	/**
	 * Factory method which should build a list of {@link Brand} based on
	 * specific data sources.
	 * 
	 * @param sourceToOmit
	 *            List of data sources that should be omitted during the Brand's
	 *            build process.
	 * @return Map with a data source as key and the build {@link Brand} as
	 *         value
	 */
	Map<String, Brand> createBrands(Set<String> sourceToOmit);
	
	
	/**
	 * Return the name of the {@link Brand} that will be constructed
	 * @return String with the name of {@link Brand}
	 */
	String getBrandName();

}
