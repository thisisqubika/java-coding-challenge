package com.mooveit.cars.xmlDomain;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlPrueba {
    public static void deserializeFromXML() throws IOException {
        try {
            // create an instance of `JAXBContext`
            JAXBContext context = JAXBContext.newInstance(XmlCatalogue.class);

            // create an instance of `Unmarshaller`
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // XML file path
            Path file =  Paths.get("C:\\Users\\LENOVO\\Documents\\git\\java-coding-challenge\\cars\\src\\main\\resources\\ford-example.xml");
            //File file = new File("book.xml");

            // convert XML file to `Book` object
            XmlCatalogue catalogue = (XmlCatalogue) unmarshaller.unmarshal(file.toFile());
            catalogue.setName(file.toFile().getName());
            // print book object
            System.out.println(catalogue);

        } catch ( JAXBException ex) {
            ex.printStackTrace();
        }





    }

    public static void main(String[] args) throws IOException {
        System.out.println("Deserializing from XML...");
        deserializeFromXML();
    }
}
