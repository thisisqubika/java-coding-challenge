package com.mooveit.cars.service;

import com.mooveit.cars.catalogue.Catalogue;

/**
 * Service methods to read and store data from xml files into the database.
 * 
 * @author Lucas Arquiel
 *
 */
public interface CatalogueService {

	/**
	 * Insert Catalogue data into the database
	 * 
	 * @param catalogueDTO
	 */
	void insertCatalogue(final Catalogue catalogueDTO);

	/**
	 * Read catalogue file
	 * 
	 * @param fileName
	 * @return
	 */
	Catalogue readFile(final String fileName);

}
