package com.mooveit.cars.utils;

import com.mooveit.cars.errors.FileException;
import com.mooveit.cars.models.CatalogueData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class FileReader {

    public static CatalogueData readFile(){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(CatalogueData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/src/main/resources/ford-example.xml");

            File xmlFile = new File(filePath);
            CatalogueData catalogueData = (CatalogueData) jaxbUnmarshaller.unmarshal(xmlFile);

            return catalogueData;
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new FileException("File could not be parsed");
        }
    }
}
