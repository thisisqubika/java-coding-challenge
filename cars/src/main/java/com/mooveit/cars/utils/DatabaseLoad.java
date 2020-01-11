package com.mooveit.cars.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.mooveit.cars.repositories.BrandRepositoryI;
import com.mooveit.cars.repositories.EngineRepositoryI;
import com.mooveit.cars.repositories.ModelRepositoryI;
import com.mooveit.cars.repositories.WheelsRepositoryI;

@Service
public class DatabaseLoad {
	
	@Autowired
  private BrandRepositoryI brandRepository;
	@Autowired
	private EngineRepositoryI engineRepository;
	@Autowired
	private WheelsRepositoryI wheelsRepository;
	@Autowired
  private ModelRepositoryI modelRepository;   
	
	public boolean modelsLoadFord(File file) {
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		try {
			
			SAXParser saxParser = saxParserFactory.newSAXParser();			
			HandlerXMLFord handlerXMLFord = new HandlerXMLFord(brandRepository, engineRepository, wheelsRepository, modelRepository);
			
			try {
				
				saxParser.parse(file, handlerXMLFord);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
		
	}

}
