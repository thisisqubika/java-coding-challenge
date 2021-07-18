package com.mooveit.cars.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WHEELS")
@XmlAccessorType (XmlAccessType.FIELD)
public class Wheel {
	
	@XmlAttribute(name = "size")
	private String size;
	@XmlAttribute(name = "type")
	private String type;
	
	public String getSize() {
		return size;
	}
	
	
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}

}
