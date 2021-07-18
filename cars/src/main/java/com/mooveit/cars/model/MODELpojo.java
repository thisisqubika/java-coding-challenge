package com.mooveit.cars.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mooveit.cars.domain.ENGINE;
import com.mooveit.cars.domain.SUBMODEL;
import com.mooveit.cars.domain.WHEELS;

public class MODELpojo implements Serializable {
	
	private static final long serialVersionUID = 5886411186065777539L;
	

	private String name;
	
	private int from;
	
	private int to;
	
	private String type;

	private String line;
	
	private SUBMODELpojo submodel;
	
	private WHEELSpojo wheels; 
	
	private ENGINEpojo engine;
	
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
	public SUBMODELpojo getSubmodel() {
		return submodel;
	}
	public void setSubmodel(SUBMODELpojo submodel) {
		this.submodel = submodel;
	}
	public WHEELSpojo getWheels() {
		return wheels;
	}
	public void setWheels(WHEELSpojo wheels) {
		this.wheels = wheels;
	}
	public ENGINEpojo getEngine() {
		return engine;
	}
	public void setEngine(ENGINEpojo engine) {
		this.engine = engine;
	}
	

	
	
	
}
