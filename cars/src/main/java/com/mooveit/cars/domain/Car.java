package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;

@Transactional
@MappedSuperclass
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	
	private String name;

	
	private Integer from;

	
	private Integer to;

	
	private String type;

	
	private String line;

	
	private String brand;

	
	private CarEngine engine;

	
	private CarWheel wheel;

	@XmlTransient
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	@Column(name = "FROM_DATE")
	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	@XmlAttribute
	@Column(name = "TO_DATE")
	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	@XmlAttribute
	@Column(name = "TYPE_CAR")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute
	@Column(name = "LINE_CAR")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@XmlAttribute
	@Column(name = "BRAND")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@XmlElement(name = "ENGINE")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ENGINE_ID", updatable = false, nullable = false)
	public CarEngine getEngine() {
		return engine;
	}

	public void setEngine(CarEngine engine) {
		this.engine = engine;
	}

	@XmlElement(name = "WHEELS")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "WHEEL_ID", updatable = false, nullable = false)
	public CarWheel getWheel() {
		return wheel;
	}

	public void setWheel(CarWheel wheel) {
		this.wheel = wheel;
	}
	
	
}
