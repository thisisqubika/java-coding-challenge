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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlRootElement(name = "WHEELS")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "WHEELS")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Wheel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "wheel")
    @JoinColumn(name = "model_id")
	@JsonIgnore
	private Model model;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "wheel")
    @JoinColumn(name = "submodel_id")
	@JsonIgnore
	private Submodel submodel;	
	
	@XmlAttribute(name = "size")
	private String wheel_size;
	
	@XmlAttribute(name = "type")
	private String wheel_type;

	public Wheel() {

	}
	
	public Wheel(String wheel_size, String wheel_type) {
		super();
		this.wheel_size = wheel_size;
		this.wheel_type = wheel_type;
	}

	public String getWheel_size() {
		return wheel_size;
	}

	public void setWheel_size(String wheel_size) {
		this.wheel_size = wheel_size;
	}

	public String getWheel_type() {
		return wheel_type;
	}

	public void setWheel_type(String wheel_type) {
		this.wheel_type = wheel_type;
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

	@Override
	public String toString() {
		return "Wheel [wheel_size=" + wheel_size + ", wheel_type=" + wheel_type + "]";
	}

	public Submodel getSubmodel() {
		return submodel;
	}

	public void setSubmodel(Submodel submodel) {
		this.submodel = submodel;
	}

}
