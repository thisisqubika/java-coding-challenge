package com.mooveit.cars.controller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.mooveit.cars.domain.ENGINE;
import com.mooveit.cars.domain.MODEL;
import com.mooveit.cars.domain.SUBMODEL;
import com.mooveit.cars.domain.WHEELS;
import com.mooveit.cars.domain.model.mapper.DomainModelObjMapper;
import com.mooveit.cars.jaxb.model.Catalogue;
import com.mooveit.cars.jaxb.model.Model;
import com.mooveit.cars.model.MODELpojo;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.service.CarsService;

@RestController
public class CarsController {

    @Autowired
    private ModelRepository ModelRepository;
    
    @Autowired
    private CarsService carsService;
    
    @GetMapping
	@RequestMapping(value = "/processCars")
	public boolean processCars() {
		
    	return carsService.processcars();

	}
    
    
    @GetMapping
	@RequestMapping(value = "/getCarById")
	public ResponseEntity<MODELpojo> getCarById(@RequestParam("id") int id) {
//   	StringWriter sw = new StringWriter();
//    	try {
//			JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			carsService.findModelById(id);
//			jaxbMarshaller.marshal(carsService.findModelById(id) , sw);
//			String xmlString = sw.toString();
//			return xmlString;
    		
    		MODEL DBModelObj = carsService.findModelById(id);
    		MODELpojo ModelObj = new MODELpojo();
    		DomainModelObjMapper mapperObj = new DomainModelObjMapper();
    		ModelObj = mapperObj.getDomainToModelObj(DBModelObj);
    		
			return new ResponseEntity<MODELpojo>(ModelObj,
					HttpStatus.OK);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
//    	return new ResponseEntity<MODEL>(carsService.findModelById(id),
//				HttpStatus.OK);
    	
	}
    
    
    @GetMapping
   	@RequestMapping(value = "/getCarByBrand")
   	public ResponseEntity<List<MODELpojo>> getCarByBrand(@RequestParam("brand") String brand) {
    	List<MODELpojo> modelObjLst = new ArrayList<MODELpojo>();
    	List<MODEL> DBModelObjLst = carsService.findModelByBrand(brand);
    	DomainModelObjMapper mapperObj = new DomainModelObjMapper();
    	for(MODEL DBModelObj: DBModelObjLst) {
    		MODELpojo ModelObj = new MODELpojo();
    		
    		ModelObj = mapperObj.getDomainToModelObj(DBModelObj);
    		modelObjLst.add(ModelObj);
    	}
		return new ResponseEntity<List<MODELpojo>>(modelObjLst,
				HttpStatus.OK);
		
		
   	}
    
    
 
	@GetMapping
	@RequestMapping(value = "/saveCar")
	public boolean saveCar() {
		
		

		
		
		
		MODEL mymodel = new MODEL();
		//mymodel.setModelid(1);
//		mymodel.setSubModelid(10);
//		mymodel.setEngineid(101);
//		mymodel.setWheelid(201);
		mymodel.setName("Aspire");
		mymodel.setFrom(1994);
		mymodel.setTo(1997);
		mymodel.setType("subcompact");
		
		//ModelRepository.save(mymodel);
		
		ENGINE engine = new ENGINE();
		engine.setPower("1400");
		engine.setType("gas");
		engine.setPrimarymodel(mymodel);
		//engine.setEngineid(mymodel.getEngineid());
		
		mymodel.setEngine(engine);
		
		WHEELS wheels = new WHEELS();
		wheels.setSize("R15");
		wheels.setType("STEEL");
		wheels.setPrimarymodel(mymodel);
		//wheels.setWheelid(mymodel.getWheelid());
			
		mymodel.setEngine(engine);
		mymodel.setWheels(wheels);
		
		MODEL mychildmodel = new MODEL();
		//mysubmodel.setModelid(2);
		//mysubmodel.setEngineid(102);
		//mysubmodel.setWheelid(202);
		mychildmodel.setName("Aspire 2");
		mychildmodel.setLine("hatchback");
		
		ENGINE subengine = new ENGINE();
		subengine.setPower("1600");
		subengine.setType("gas");
		subengine.setPrimarymodel(mychildmodel);
		
		mychildmodel.setEngine(subengine);
		
		WHEELS subwheels = new WHEELS();
		subwheels.setSize("R15");
		subwheels.setType("STEEL");
		subwheels.setPrimarymodel(mychildmodel);
		
		mychildmodel.setWheels(subwheels);
		
		//mysubmodel.setEngine(subengine);
		//mysubmodel.setWheels(subwheels);
		
		
		
		//ModelRepository.save(mysubmodel);
		//submodel.setModelid(10000);
		
		SUBMODEL submodel = new SUBMODEL();	
		
		submodel.setPrimarymodel(mymodel);	
		
		
		mymodel.setSubmodel(submodel);
		
		
		mychildmodel.setParentsubmodel(submodel);
		
		List<MODEL> childmodellist = new ArrayList<MODEL>();
		childmodellist.add(mychildmodel);
		
		submodel.setChildmodellst(childmodellist);
		
		
		ModelRepository.save(mymodel);
		//ModelRepository.save(mychildmodel);

		
		
		return true;
	}
}
