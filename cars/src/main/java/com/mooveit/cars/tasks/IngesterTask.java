package com.mooveit.cars.tasks;

import com.mooveit.cars.tasks.exceptions.FileReaderException;
import com.mooveit.cars.tasks.fileReaders.FileReaderService;
import com.mooveit.cars.tasks.model.CarSpecifications;
import com.mooveit.cars.tasks.service.IngesterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class to deal with the ingestion of Specification files.
 */
public abstract class IngesterTask {

    final Logger log = LoggerFactory.getLogger(IngesterTask.class);

    @Autowired
    private FileReaderService fileReaderService;

    /**
     * Ingests a Specification file.
     */
    public abstract void ingestFile();

    /**
     *  Generic file ingestor.
     *
     * @param filePath      String File to be ingested's path.
     * @param service       {{IngesterService}}     The service that will handle the file persistence.
     */
    public void ingestFile(String filePath, IngesterService service) {
        try {
            log.info("Attempting to ingest a file.");
            CarSpecifications carSpecifications = fileReaderService.readFile(filePath);
            log.info("Specification file ingested sucessfully.");
            service.processAndSave(carSpecifications);
        } catch (FileReaderException fe) {
            log.error("An unexpected error occurred when trying to read the file", fe);
        } catch (Exception e) {
            log.error("An unexpected error occurred when trying to save the file into the database.", e);
        }
    }
}
