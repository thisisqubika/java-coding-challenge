package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Transactional
@XmlRootElement(name = "MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "CAR_SUB_MODEL")
public class CarSubModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private int id;

	@XmlAttribute
	@Column(name = "NAME")
	private String name;

	@XmlAttribute
	@Column(name = "FROM_DATE")
	private int from;

	@XmlAttribute
	@Column(name = "TO_DATE")
	private int to;

	@XmlAttribute
	@Column(name = "TYPE_CAR")
	private String type;

	@XmlAttribute
	@Column(name = "LINE_CAR")
	private String line;

	@XmlAttribute
	@Column(name = "BRAND")
	private String brand;

	@XmlElement(name = "ENGINE")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ENGINE_ID", updatable = false, nullable = false)
	private CarEngine engine;

	@XmlElement(name = "WHEELS")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "WHEEL_ID", updatable = false, nullable = false)
	private CarWheel wheel;

	@ManyToOne
	@JoinColumn(name = "ID_MODEL", insertable = true, updatable = true)
	@JsonIgnore
	private CarModel model;

	public CarSubModel() {

	}
}
