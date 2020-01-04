package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlRootElement(name = "SUBMODELS")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "SUBMODELS")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Submodel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long submodel_id;
	
	@XmlAttribute(name = "name")
	private String submodel_name;
	
	@XmlAttribute(name = "line")
	private String submodel_line;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
	@JsonIgnore
    private Model model_id;
	
	@OneToOne
	@JoinColumn(name = "wheel_id")
	private Wheel wheel;
	
	@OneToOne
	@JoinColumn(name = "engine_id")
	private Engine engine;

	public Long getSubmodel_id() {
		return submodel_id;
	}

	public void setSubmodel_id(Long submodel_id) {
		this.submodel_id = submodel_id;
	}

	public String getSubmodel_name() {
		return submodel_name;
	}

	public void setSubmodel_name(String submodel_name) {
		this.submodel_name = submodel_name;
	}

	public String getSubmodel_line() {
		return submodel_line;
	}

	public void setSubmodel_line(String submodel_line) {
		this.submodel_line = submodel_line;
	}

	public Wheel getWheel() {
		return wheel;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Submodel(String submodel_name, String submodel_line) {
		super();
		this.submodel_name = submodel_name;
		this.submodel_line = submodel_line;
	}

	public Submodel() {
		super();
	}

	public Model getModel_id() {
		return model_id;
	}

	public void setModel_id(Model model_id) {
		this.model_id = model_id;
	}

}
