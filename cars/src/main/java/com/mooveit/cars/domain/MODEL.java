package com.mooveit.cars.domain;

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

@Entity
@Table(name = "MODEL")
public class MODEL implements Serializable {
	
	private static final long serialVersionUID = 5886434486065777539L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MODEL_ID")
	private int modelid;
	
//	@Column(name = "SUB_MODEL_ID")
//	private int subModelid;
//	
//	@Column(name = "ENGINE_ID")
//	private int engineid;
//	
//	@Column(name = "WHEEL_ID")
//	private int wheelid;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "FRM")
	private int from;
	
	@Column(name = "TO")
	private int to;
	
	@Column(name = "TYPE")
	private String type;

	@Column(name = "LINE")
	private String line;
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="primarymodel")
	private SUBMODEL submodel;
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="primarymodel")
	private WHEELS wheels; 
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="primarymodel")
	private ENGINE engine;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_SUB_MODEL_ID")
	private SUBMODEL parentsubmodel;
	
	public int getModelid() {
		return modelid;
	}
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
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
	
	public WHEELS getWheels() {
		return wheels;
	}
	public void setWheels(WHEELS wheels) {
		this.wheels = wheels;
	}
	public ENGINE getEngine() {
		return engine;
	}
	public void setEngine(ENGINE engine) {
		this.engine = engine;
	}
	public SUBMODEL getSubmodel() {
		return submodel;
	}
	public void setSubmodel(SUBMODEL submodel) {
		this.submodel = submodel;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public SUBMODEL getParentsubmodel() {
		return parentsubmodel;
	}
	public void setParentsubmodel(SUBMODEL parentsubmodel) {
		this.parentsubmodel = parentsubmodel;
	}

	
	
	
}
