package com.mooveit.cars.catalogue;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@XmlRootElement(name = "CATALOGUE")
public class Catalogue {

	private String brand;

	private List<Model> models = new ArrayList<Model>();

	@XmlElement(name = "MODEL")
	public List<Model> getModels() {
		return models;
	}

	public void setModels(final List<Model> models) {
		this.models = models;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(final String brand) {
		this.brand = brand;
	}

}
