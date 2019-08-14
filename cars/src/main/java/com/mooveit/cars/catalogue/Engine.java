package com.mooveit.cars.catalogue;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@XmlRootElement
public class Engine {

	private Long power;

	private String type;

	@XmlAttribute(name = "power")
	public Long getPower() {
		return power;
	}

	public void setPower(final Long power) {
		this.power = power;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
