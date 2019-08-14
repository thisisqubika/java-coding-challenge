package com.mooveit.cars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@Entity(name = "model")
public class ModelTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String brand;

	private Long year_from;

	private Long year_to;

	private String type;

	@OneToOne
	@JoinColumn(name = "engine_id", referencedColumnName = "id")
	private EngineTable engine;

	@OneToOne
	@JoinColumn(name = "wheels_id", referencedColumnName = "id")
	private WheelsTable wheels;

	/**
	 * default constructor
	 */
	protected ModelTable() {
		// won't be used
	}

	/**
	 * constructor without id, engine and wheels used to validate if the model is
	 * already in the database
	 * 
	 * @param name
	 * @param brand
	 * @param year_from
	 * @param year_to
	 * @param type
	 */
	public ModelTable(final String name, final String brand, final Long year_from, final Long year_to,
			final String type) {
		super();
		this.name = name;
		this.brand = brand;
		this.year_from = year_from;
		this.year_to = year_to;
		this.type = type;
	}

	/**
	 * constructor without id
	 * 
	 * @param name
	 * @param brand
	 * @param year_from
	 * @param year_to
	 * @param type
	 * @param engine
	 * @param wheels
	 */
	public ModelTable(final String name, final String brand, final Long year_from, final Long year_to,
			final String type, final EngineTable engine, final WheelsTable wheels) {
		super();
		this.name = name;
		this.brand = brand;
		this.year_from = year_from;
		this.year_to = year_to;
		this.type = type;
		this.engine = engine;
		this.wheels = wheels;
	}

	/**
	 * constructor
	 * 
	 * @param id
	 * @param name
	 * @param brand
	 * @param year_from
	 * @param year_to
	 * @param type
	 * @param engine
	 * @param wheels
	 */
	public ModelTable(final Long id, final String name, final String brand, final Long year_from, final Long year_to,
			final String type, final EngineTable engine, final WheelsTable wheels) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.year_from = year_from;
		this.year_to = year_to;
		this.type = type;
		this.engine = engine;
		this.wheels = wheels;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(final String brand) {
		this.brand = brand;
	}

	public Long getYear_from() {
		return year_from;
	}

	public void setYear_from(final Long year_from) {
		this.year_from = year_from;
	}

	public Long getYear_to() {
		return year_to;
	}

	public void setYear_to(final Long year_to) {
		this.year_to = year_to;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public EngineTable getEngine() {
		return engine;
	}

	public void setEngine(final EngineTable engine) {
		this.engine = engine;
	}

	public WheelsTable getWheels() {
		return wheels;
	}

	public void setWheels(final WheelsTable wheels) {
		this.wheels = wheels;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((year_from == null) ? 0 : year_from.hashCode());
		result = prime * result + ((year_to == null) ? 0 : year_to.hashCode());
		return result;
	}

	/**
	 * equals method used to validate if a model is already in the database.
	 * This implementation only validates brand, name, type, from and to values.
	 * 
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ModelTable other = (ModelTable) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (year_from == null) {
			if (other.year_from != null)
				return false;
		} else if (!year_from.equals(other.year_from))
			return false;
		if (year_to == null) {
			if (other.year_to != null)
				return false;
		} else if (!year_to.equals(other.year_to))
			return false;
		return true;
	}

}
