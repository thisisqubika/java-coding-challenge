package com.mooveit.cars.catalogue;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@XmlRootElement
public class Submodel {

	private String name;

	private Long from;

	private Long to;

	private String type;

	private Engine engine;

	private Wheels wheels;

	private String line;

	@XmlAttribute(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(final String line) {
		this.line = line;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@XmlAttribute(name = "from")
	public Long getFrom() {
		return from;
	}

	public void setFrom(final Long from) {
		this.from = from;
	}

	@XmlAttribute(name = "to")
	public Long getTo() {
		return to;
	}

	public void setTo(final Long to) {
		this.to = to;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@XmlElement(name = "ENGINE")
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(final Engine engine) {
		this.engine = engine;
	}

	@XmlElement(name = "WHEELS")
	public Wheels getWheels() {
		return wheels;
	}

	public void setWheels(final Wheels wheels) {
		this.wheels = wheels;
	}

}
