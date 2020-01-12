package com.mooveit.cars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "CATALOGUE")
public class CarCatalogue  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<CarModel> models = new ArrayList<CarModel>();
	
	@XmlElement(name = "MODEL")
	public List<CarModel> getModels() {
		return models;
	}
	public void setModels(List<CarModel> models) {
		this.models = models;
	}

}
