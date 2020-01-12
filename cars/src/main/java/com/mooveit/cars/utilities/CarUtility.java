package com.mooveit.cars.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mooveit.cars.domain.CarCatalogue;

public class CarUtility {


	public static CarCatalogue fromXML(InputStream in) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CarCatalogue.class);
        Unmarshaller um = context.createUnmarshaller();
        return (CarCatalogue) um.unmarshal(in);
    }
	
	public static String InputStreamToString(InputStream in) throws Exception {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(in))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
	}
}
