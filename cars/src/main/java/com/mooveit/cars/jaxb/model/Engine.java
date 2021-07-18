package com.mooveit.cars.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ENGINE")
@XmlAccessorType (XmlAccessType.FIELD)
public class Engine {

	@XmlAttribute(name = "power")
	private String power;
	@XmlAttribute(name = "type")
	private String type;
	
	public String getPower() {
		return power;
	}
	
	
	public void setPower(String power) {
		this.power = power;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
