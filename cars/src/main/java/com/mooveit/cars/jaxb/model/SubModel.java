package com.mooveit.cars.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SUBMODELS")
@XmlAccessorType (XmlAccessType.FIELD)
public class SubModel {
	
	@XmlElement(name="MODEL")
	private List<Model> submodels;

	public List<Model> getSubmodels() {
		return submodels;
	}

	public void setSubmodels(List<Model> submodels) {
		this.submodels = submodels;
	}
	

}
