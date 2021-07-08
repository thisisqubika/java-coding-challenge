package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Model")
@XmlRootElement(name = "MODEL")
//@SecondaryTable(name = "SUBMODEL", pkJoinColumns = @PrimaryKeyJoinColumn(name = "model_name"))
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "model_name")
	@XmlAttribute(name = "name")
	private String name;

	@Column(name = "model_from")
	@XmlAttribute(name = "from")
	private String froom;

	@Column(name = "to")
	@XmlAttribute(name = "to")
	private String too;

	@Column(name = "type")
	@XmlAttribute(name = "type")
	private String type;

	@Embedded
	@XmlElement(name = "ENGINE")
	private Engine engine;

	@Embedded 
	@XmlElement(name = "WHEELS")
	private Wheel wheel;

//	private ArrayList<Model> models = new ArrayList<>();
//
//	@XmlElementWrapper(name = "submodels")
//	@XmlElement(name = "model")
//	public ArrayList<Model> getModels() {
//		return models;
//	}
//
//	public void setModels(ArrayList<Model> models) {
//		this.models = models;
//	}

	public Car() {
		super();
	}

	public Car(String name, String from, String to, String type, Engine engine, Wheel wheel) {
		super();
		this.name = name;
		this.froom = from;
		this.too = to;
		this.type = type;
		this.engine = engine;
		this.wheel = wheel;
	}

	//Setters and Getters

	@Override
	public String toString() {
		return "Model [name=" + name + ", from=" + froom + ", to="
				+ too + ", type ="+ type + "]";
	}
}


@XmlRootElement(name = "WHEELS")
class Wheel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "wheel_size")
	@XmlAttribute(name = "size")
	String size;

	@Column(name = "wheel_type")
	@XmlAttribute(name = "type")
	String type;

	public Wheel() {
		super();
	}

	public Wheel(String size, String type) {
		super();
		this.size = size;
		this.type = type;
	}

	//Setters and Getters

	@Override
	public String toString() {
		return "Wheel [size=" + size + ", type=" + type + "]";
	}

}


@XmlRootElement(name = "ENGINE")
class Engine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "engine_power")
	@XmlAttribute(name = "power")
	String power;

	@Column(name = "engine_type")
	@XmlAttribute(name = "type")
	String type;

	public Engine() {
		super();
	}

	public Engine(String power, String type) {
		super();
		this.power = power;
		this.type = type;
	}

	//Setters and Getters

	@Override
	public String toString() {
		return "Engine [power=" + power + ", type=" + type + "]";
	}

}

/*@XmlRootElement(name = "Model")
class Model implements Serializable {

	private static final long serialVersionUID = 1L;

	//@Column(name = "submodel_name")
	@XmlAttribute(name = "name")
	String name;

	//@Column(name = "line")
	@XmlAttribute(name = "line")
	String line;

	public Model() {
		super();
	}

	public Model(String name, String line) {
		super();
		this.name = name;
		this.line = line;
	}

	//Setters and Getters

	@Override
	public String toString() {
		return "Model [name=" + name + ", line=" + line + "]";
	}
}*/


