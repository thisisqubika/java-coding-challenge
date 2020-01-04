package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "ENGINE")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "ENGINES")
public class Engine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "engine")
    @JoinColumn(name = "model_id")
	@JsonIgnore
	private Model model;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "engine")
    @JoinColumn(name = "submodel_id")
	@JsonIgnore
	private Submodel submodel;
	
	@XmlAttribute(name = "power")
	private int engine_power;
	@XmlAttribute(name = "type")
	private String engine_type;

	public Engine() {

	}
	
	public Engine(int engine_power, String engine_type) {
		super();
		this.engine_power = engine_power;
		this.engine_type = engine_type;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getEngine_power() {
		return engine_power;
	}

	public void setEngine_power(int engine_power) {
		this.engine_power = engine_power;
	}

	public String getEngine_type() {
		return engine_type;
	}

	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}

	public Submodel getSubmodel() {
		return submodel;
	}

	public void setSubmodel(Submodel submodel) {
		this.submodel = submodel;
	}

}
