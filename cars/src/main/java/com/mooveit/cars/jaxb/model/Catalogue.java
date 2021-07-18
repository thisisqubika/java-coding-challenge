package com.mooveit.cars.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CATALOGUE")
@XmlAccessorType (XmlAccessType.FIELD)
public class Catalogue {

	@XmlElement(name="MODEL")
	private List<Model> models;

	public List<Model> getModels() {
		return models;
	}
	
	public void setModels(List<Model> models) {
		this.models = models;
	}
	
}
