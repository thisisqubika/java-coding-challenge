package com.mooveit.cars.service;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooveit.cars.catalogue.Catalogue;
import com.mooveit.cars.catalogue.Model;
import com.mooveit.cars.catalogue.Submodel;
import com.mooveit.cars.domain.EngineTable;
import com.mooveit.cars.domain.ModelTable;
import com.mooveit.cars.domain.SubmodelTable;
import com.mooveit.cars.domain.WheelsTable;
import com.mooveit.cars.repositories.EngineRepository;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.repositories.SubmodelRepository;
import com.mooveit.cars.repositories.WheelsRepository;

/**
 * Service methods to read and store data from xml files into the database.
 * 
 * @author Lucas Arquiel
 * 
 */
@Service
public class CatalogueServiceImpl implements CatalogueService {

	private static final Logger LOG = LoggerFactory.getLogger(CatalogueServiceImpl.class);

	@Autowired
	private ModelRepository modelRepository;

	@Autowired
	private SubmodelRepository submodelRepository;

	@Autowired
	private EngineRepository engineRepository;

	@Autowired
	private WheelsRepository wheelsRepository;

	@Autowired
	private CarsService carsService;

	/**
	 * Insert Catalogue data into the database
	 * 
	 * @param catalogueDTO
	 */
	@Transactional
	public void insertCatalogue(final Catalogue catalogueDTO) {

		LOG.info("Starting to insert catalogue...");
		for (final Model modelDTO : catalogueDTO.getModels()) {
			if (!validateAlreadyInsertedModel(new ModelTable(modelDTO.getName(), catalogueDTO.getBrand(),
					modelDTO.getFrom(), modelDTO.getTo(), modelDTO.getType()))) {

				EngineTable engine = null;
				WheelsTable wheels = null;
				ModelTable model = null;
				if (modelDTO.getEngine() != null) {
					engine = createEngine(
							new EngineTable(modelDTO.getEngine().getPower(), modelDTO.getEngine().getType()));
				}
				if (modelDTO.getWheels() != null) {
					wheels = createWheels(
							new WheelsTable(modelDTO.getWheels().getSize(), modelDTO.getWheels().getType()));
				}

				model = createModel(new ModelTable(modelDTO.getName(), catalogueDTO.getBrand(), modelDTO.getFrom(),
						modelDTO.getTo(), modelDTO.getType(), engine, wheels));

				if (modelDTO.getSubmodels() != null && modelDTO.getSubmodels().getSubmodels() != null) {
					SubmodelTable submodel = null;
					for (final Submodel submodelDTO : modelDTO.getSubmodels().getSubmodels()) {
						EngineTable submodelEngine = null;
						WheelsTable submodelWheels = null;
						if (submodelDTO.getEngine() != null) {
							submodelEngine = createEngine(new EngineTable(submodelDTO.getEngine().getPower(),
									submodelDTO.getEngine().getType()));
						}
						if (submodelDTO.getWheels() != null) {
							submodelWheels = createWheels(new WheelsTable(submodelDTO.getWheels().getSize(),
									submodelDTO.getWheels().getType()));
						}
						submodel = createSubmodel(new SubmodelTable(submodelDTO.getName(), submodelDTO.getLine(),
								submodelDTO.getFrom(), submodelDTO.getTo(), submodelEngine, submodelWheels, model));

					}

				}
			} else {
				LOG.info("Model with name {}, brand {}, type {}, from {}, to {} is already in the database",
						modelDTO.getName(), catalogueDTO.getBrand(), modelDTO.getType(), modelDTO.getFrom(),
						modelDTO.getTo());
			}

		}
		LOG.info("Finished inserting catalogue...");

	}

	/**
	 * validates if a model is already in the database
	 * 
	 * @param newModel
	 * @return true if the new model is already inserted; otherwise return false
	 */
	private Boolean validateAlreadyInsertedModel(final ModelTable newModel) {
		final List<ModelTable> allInsertedModels = carsService.getModels();
		for (final ModelTable insertedModel : allInsertedModels) {
			if (insertedModel.equals(newModel)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Insert into model table
	 * 
	 * @param model
	 * @return
	 */
	@Transactional
	private ModelTable createModel(final ModelTable model) {
		return modelRepository.save(model);
	}

	/**
	 * Insert into submodel table
	 * 
	 * @param submodel
	 * @return
	 */
	@Transactional
	private SubmodelTable createSubmodel(final SubmodelTable submodel) {
		return submodelRepository.save(submodel);
	}

	/**
	 * Insert into engine table
	 * 
	 * @param engine
	 * @return
	 */
	@Transactional
	private EngineTable createEngine(final EngineTable engine) {
		return engineRepository.save(engine);
	}

	/**
	 * Insert into wheels table
	 * 
	 * @param wheels
	 * @return
	 */
	@Transactional
	private WheelsTable createWheels(final WheelsTable wheels) {
		return wheelsRepository.save(wheels);
	}

	/**
	 * Read catalogue file
	 * 
	 * @param fileName
	 * @return
	 */
	public Catalogue readFile(final String fileName) {

		final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		final File file = new File(classLoader.getResource(fileName).getFile());

		Catalogue catalogue = null;
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(Catalogue.class);

			final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			catalogue = (Catalogue) jaxbUnmarshaller.unmarshal(file);
			catalogue.setBrand(getBrandFromFile(file.getName()));

		} catch (JAXBException e) {
			LOG.error("JAXBException: " + e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return catalogue;
	}

	/**
	 * Get brand from filename
	 * 
	 * @param filename
	 * @return
	 */
	private String getBrandFromFile(final String filename) {
		return filename.substring(0, filename.indexOf(".xml"));
	}

}
