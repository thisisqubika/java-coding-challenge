package com.mooveit.cars.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MODEL")
@XmlAccessorType (XmlAccessType.FIELD)
public class Model {

	@XmlAttribute(name = "name")
	private String name;
	@XmlAttribute(name = "from")
	private int from;
	@XmlAttribute(name = "to")
	private int to;
	@XmlAttribute(name = "type")
	private String type;
	@XmlAttribute(name = "line")
	private String line;
	
	@XmlElement(name="ENGINE")
	private Engine engine;
	@XmlElement(name="WHEELS")
	private Wheel wheel;
	@XmlElement(name="SUBMODELS")
	private SubModel submodel;
	
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public int getFrom() {
		return from;
	}


	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	
	public void setTo(int to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Wheel getWheel() {
		return wheel;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}

	public SubModel getSubmodel() {
		return submodel;
	}

	public void setSubmodel(SubModel submodel) {
		this.submodel = submodel;
	}

}