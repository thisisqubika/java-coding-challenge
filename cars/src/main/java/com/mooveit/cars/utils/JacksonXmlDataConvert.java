package com.mooveit.cars.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mooveit.cars.dto.CatalogueDTO;

public class JacksonXmlDataConvert {

	public static JacksonXmlDataConvert getInstance() {
		return new JacksonXmlDataConvert();
	}
	/**
	 * Method to process xml data into a CatalogueDTO
	 * with Jackson lib
	 * @param url XmlFile directory
	 * @return {@link CatalogueDTO}
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CatalogueDTO getFordDataXml (String url) throws FileNotFoundException, IOException {
		File file = new File(url);
		XmlMapper xmlMapper = new XmlMapper();		
		String xml = inputStreamToString(new FileInputStream(file));
		CatalogueDTO catalogueData = xmlMapper.readValue(xml, CatalogueDTO.class);		
		return catalogueData;
	}
	
	public String inputStreamToString(InputStream is) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    String line;
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    while ((line = br.readLine()) != null) {
	        sb.append(line);
	    }
	    br.close();
	    return sb.toString();
	}
}
