package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Transactional
public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_MODEL")
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

	@XmlElement(name = "ENGINE")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ENGINE_ID", updatable = false, nullable = false)
	private CarEngine engine;

	@XmlElement(name = "WHEELS")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "WHEEL_ID", updatable = false, nullable = false)
	private CarWheel wheel;
}
