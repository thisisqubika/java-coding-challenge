package com.mooveit.cars.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlRootElement(name = "MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "MODELS")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long model_id;
	
	@XmlAttribute(name = "name")
	private String model_name;
	
	@XmlAttribute(name = "from")
	@Column(length = 4)
	private Integer model_from;
	
	@XmlAttribute(name = "to")
	@Column(length = 4)
	private Integer model_to;
	
	@XmlAttribute(name = "type")
	private String model_type;
	
	@XmlElementWrapper(name="SUBMODELS")
    @XmlElement(name="MODEL")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "model_id")
	private List<Submodel> submodels;

	@XmlElement(name = "WHEELS")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "wheel_id")
	private Wheel wheel;
	
	@XmlElement(name = "ENGINE")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	@JoinColumn(name = "engine_id")
	private Engine engine;

	public Long getModel_id() {
		return model_id;
	}

	public void setModel_id(Long model_id) {
		this.model_id = model_id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public Integer getModel_from() {
		return model_from;
	}

	public void setModel_from(Integer model_from) {
		this.model_from = model_from;
	}

	public Integer getModel_to() {
		return model_to;
	}

	public void setModel_to(Integer model_to) {
		this.model_to = model_to;
	}

	public String getModel_type() {
		return model_type;
	}

	public void setModel_type(String model_type) {
		this.model_type = model_type;
	}

	public List<Submodel> getSubmodels() {
		return submodels;
	}

	public void setSubmodels(List<Submodel> submodels) {
		this.submodels = submodels;
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

	public Model(String model_name, Integer model_from, Integer model_to, String model_type) {
		super();
		this.model_name = model_name;
		this.model_from = model_from;
		this.model_to = model_to;
		this.model_type = model_type;
	}

	public Model() {
		
	}

	@Override
	public String toString() {
		return "Model [model_name=" + model_name + ", model_from=" + model_from + ", model_to=" + model_to
				+ ", model_type=" + model_type + "]";
	}

}
