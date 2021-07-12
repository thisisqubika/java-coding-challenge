package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "car_brand")
public class CarBrand implements Serializable {
	private static final long serialVersionUID = 5719261850801444936L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@NotNull
	@OneToOne(targetEntity = Engine.class, cascade = CascadeType.ALL)
	private Catalogue catalogue;

	public CarBrand() {
	}

	public CarBrand(String name, Catalogue catalogue) {
		this.name = name;
		this.catalogue = catalogue;
	}

	@Override
	public String toString() {
		return "CarBrand{" + "id=" + id + ", name='" + name + '\'' + ", catalogue=" + catalogue + '}';
	}
}
