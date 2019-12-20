package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Transactional
@XmlRootElement(name = "WHEEL")
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "CAR_WHEEL")
public class CarWheel implements Serializable {

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
	@Column(name = "SIZE")
	private String size;

	@XmlAttribute
	@Column(name = "TYPE_CAR")
	private String type;
}
