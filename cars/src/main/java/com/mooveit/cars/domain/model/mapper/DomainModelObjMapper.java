package com.mooveit.cars.domain.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mooveit.cars.domain.ENGINE;
import com.mooveit.cars.domain.MODEL;
import com.mooveit.cars.domain.SUBMODEL;
import com.mooveit.cars.domain.WHEELS;
import com.mooveit.cars.model.ENGINEpojo;
import com.mooveit.cars.model.MODELpojo;
import com.mooveit.cars.model.SUBMODELpojo;
import com.mooveit.cars.model.WHEELSpojo;

public class DomainModelObjMapper {
	
	public MODELpojo getDomainToModelObj(MODEL DBModelObj) {
		MODELpojo ModelObj = new MODELpojo();
		
		ModelObj.setFrom(DBModelObj.getFrom());
		ModelObj.setTo(DBModelObj.getTo());
		ModelObj.setType(DBModelObj.getType());
		ModelObj.setLine(DBModelObj.getLine());
		ModelObj.setName(DBModelObj.getName());
		
		ModelObj.setEngine(getEnginePojoFromDBEngineObj(DBModelObj.getEngine()));
		ModelObj.setWheels(getWheelsPojoFromDBWheelsObj(DBModelObj.getWheels()));
		ModelObj.setSubmodel(getSubMdlPojoFromDBSubMdlObj(DBModelObj.getSubmodel()));
		
		return ModelObj;
	}
	
	private ENGINEpojo getEnginePojoFromDBEngineObj(ENGINE DBEngineObj) {
		ENGINEpojo EngineObj = new ENGINEpojo();
		
		EngineObj.setPower(DBEngineObj.getPower());
		EngineObj.setType(DBEngineObj.getType());
		return EngineObj;
	}
	
	private WHEELSpojo getWheelsPojoFromDBWheelsObj(WHEELS DBWHeelsObj) {
		WHEELSpojo WheelsObj = new WHEELSpojo();
		
		WheelsObj.setSize(DBWHeelsObj.getSize());
		WheelsObj.setType(DBWHeelsObj.getType());
		
		return WheelsObj;
	}
	
	
	private SUBMODELpojo getSubMdlPojoFromDBSubMdlObj(SUBMODEL DBSubModelObj) {
		SUBMODELpojo SubModelObj = new SUBMODELpojo();
		List<MODELpojo> modelLst = new ArrayList<MODELpojo>();
		if (DBSubModelObj != null && DBSubModelObj.getChildmodellst() != null) {
			for (MODEL childModel : DBSubModelObj.getChildmodellst()) {
				modelLst.add(getDomainToModelObj(childModel));
			}
			SubModelObj.setChildmodellst(modelLst);
		}
		else {
			SubModelObj = null;
		}
		
		return SubModelObj;
	}
	
	
	

}

