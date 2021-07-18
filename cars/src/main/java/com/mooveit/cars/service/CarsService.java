package com.mooveit.cars.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.ENGINE;
import com.mooveit.cars.domain.MODEL;
import com.mooveit.cars.domain.SUBMODEL;
import com.mooveit.cars.domain.WHEELS;
import com.mooveit.cars.jaxb.model.Catalogue;
import com.mooveit.cars.jaxb.model.Engine;
import com.mooveit.cars.jaxb.model.Model;
import com.mooveit.cars.jaxb.model.SubModel;
import com.mooveit.cars.jaxb.model.Wheel;
import com.mooveit.cars.repositories.ModelRepository;

@Service
public class CarsService {

	@Autowired
	ResourceLoader resourceLoader;
	
	 @Autowired
	 private ModelRepository ModelRepository;
	    
	
	
	public boolean processcars() {
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalogue.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Catalogue catalog = (Catalogue) jaxbUnmarshaller.unmarshal(resourceLoader.getResource("classpath:ford-example.xml").getFile());
			System.out.println("completed processing");
			
			for(Model data : catalog.getModels()) {
				ModelRepository.save(mapModelForDB(data));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		return true;
		
	}
	
	
	public MODEL findModelById(Integer id) {
        return ModelRepository.findById(id).orElse(null); 
    }
	
	
	public List<MODEL> findModelByBrand(String brand) {
        return ModelRepository.findByName(brand); 
    }
	
	private MODEL mapModelForDB(Model model) {
		MODEL retval = new MODEL();
		retval.setFrom(model.getFrom());
		retval.setLine(model.getLine());
		retval.setName(model.getName());
		retval.setTo(model.getTo());
		retval.setType(model.getType());
		retval.setEngine(mapEngineForDB(model.getEngine(),retval));
		retval.setWheels(mapWheelsForDB(model.getWheel(),retval));
		retval.setSubmodel(mapSubModelsDB(model.getSubmodel(),retval));
		if(retval.getSubmodel() != null)
			for(MODEL childModel: retval.getSubmodel().getChildmodellst()) {
				childModel.setParentsubmodel(retval.getSubmodel());
			}
	
		return retval;
	}
	
	private ENGINE mapEngineForDB(Engine engine,MODEL model) {
		ENGINE retval = new ENGINE();
		retval.setPower(engine.getPower());
		retval.setType(engine.getType());
		retval.setPrimarymodel(model);
		return retval;
	}
	
	private WHEELS mapWheelsForDB(Wheel wheel,MODEL model) {
		WHEELS retval = new WHEELS();
		retval.setSize(wheel.getSize());
		retval.setType(wheel.getType());
		retval.setPrimarymodel(model);
		return retval;
	}
	
	private SUBMODEL mapSubModelsDB(SubModel submodel,MODEL model) {
		SUBMODEL retval = new SUBMODEL();
		
		if (null != submodel ) {
			List<MODEL> childmodellst = new ArrayList<MODEL>();
			for( Model data : submodel.getSubmodels()) {
				MODEL dbmodel = mapModelForDB(data);
				//ModelRepository.save(dbmodel);
				childmodellst.add(dbmodel);
			}
			retval.setChildmodellst(childmodellst);
			retval.setPrimarymodel(model);
			return retval;
		}else 
		{
			return null;
		}
		
		
		
	}
	
	
}
