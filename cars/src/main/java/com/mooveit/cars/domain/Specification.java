package com.mooveit.cars.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Base setup for a car model.
 *
 */
@Entity
@DiscriminatorValue("specification")
public class Specification extends AbstractSpec {

	/**
	 * Cars model type
	 */
	@NotNull
	private String type;

	/**
	 * Car model brand
	 */
	@ManyToOne
	@NotNull
	private Brand brand;

	/**
	 * List of modifications per each car model
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private List<Modification> modifications;

	protected Specification() {
		super();
	}

	public Specification(Brand brand, String name, Integer from, Integer to, String type, Engine engine, Wheel wheel) {
		super(name, from, to, engine, wheel);
		this.type = type;
		this.modifications = new ArrayList<Modification>();
		this.brand = brand;
	}

	public void addModification(Modification modification) {
		this.modifications.add(modification);
	}

	public Modification getModification(int index) {
		return this.modifications.get(index);
	}

	public Stream<Modification> getModifications() {
		return this.modifications.stream();
	}

	public boolean hasModifications() {
		return !this.modifications.isEmpty();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	protected void setModifications(List<Modification> modifications) {
		this.modifications = modifications;
	}

}
