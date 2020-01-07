package com.mooveit.cars.tasks.fileReaders;

import com.mooveit.cars.tasks.exceptions.FileReaderException;
import com.mooveit.cars.tasks.model.CarSpecifications;
import com.mooveit.cars.tasks.model.Catalogue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Reads an xml file and returns Catalogue populated with the data from the xml.
 */
@Service
public class FileReaderService {

    final Logger log = LoggerFactory.getLogger(FileReaderService.class);

    /**
     * Reads a file and returns a {{Catalogue}} containing the information of that file.
     *
     * @return
     * @throws FileReaderException  if there is an error reading the file.
     */
    public CarSpecifications readFile(String filePath) throws FileReaderException {
        try {
            JAXBContext context = JAXBContext.newInstance(Catalogue.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();

            String path = new File("").getAbsolutePath().concat(filePath);
            File xmlFile = new File(path);

            return (CarSpecifications) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (Exception e) {
            log.error("An exception occured when trying to parse the file.");
            throw new FileReaderException(e.toString());
        }
    }

}
