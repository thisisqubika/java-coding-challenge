package com.mooveit.cars.catalogue;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@XmlRootElement
public class Wheels {

	private String size;

	private String type;

	@XmlAttribute(name = "size")
	public String getSize() {
		return size;
	}

	public void setSize(final String size) {
		this.size = size;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
