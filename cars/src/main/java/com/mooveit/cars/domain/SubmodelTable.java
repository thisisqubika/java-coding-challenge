package com.mooveit.cars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@Entity(name = "submodel")
public class SubmodelTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String line;

	private Long year_from;

	private Long year_to;

	@OneToOne
	@JoinColumn(name = "engine_id", referencedColumnName = "id")
	private EngineTable engine;

	@OneToOne
	@JoinColumn(name = "wheels_id", referencedColumnName = "id")
	private WheelsTable wheels;

	@ManyToOne
	@JoinColumn(name = "model_id", referencedColumnName = "id")
	private ModelTable model;

	/**
	 * default constructor
	 */
	protected SubmodelTable() {
		// won't be used
	}

	/**
	 * constructor
	 * 
	 * @param name
	 * @param line
	 * @param year_from
	 * @param year_to
	 * @param engine
	 * @param wheels
	 * @param model
	 */
	public SubmodelTable(final String name, final String line, final Long year_from, final Long year_to,
			final EngineTable engine, final WheelsTable wheels, final ModelTable model) {
		super();
		this.name = name;
		this.line = line;
		this.year_from = year_from;
		this.year_to = year_to;
		this.engine = engine;
		this.wheels = wheels;
		this.model = model;
	}

	/**
	 * constructor
	 * 
	 * @param id
	 * @param name
	 * @param line
	 * @param year_from
	 * @param year_to
	 * @param engine
	 * @param wheels
	 * @param model
	 */
	public SubmodelTable(final Long id, final String name, final String line, final Long year_from, final Long year_to,
			final EngineTable engine, final WheelsTable wheels, final ModelTable model) {
		super();
		this.id = id;
		this.name = name;
		this.line = line;
		this.year_from = year_from;
		this.year_to = year_to;
		this.engine = engine;
		this.wheels = wheels;
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getLine() {
		return line;
	}

	public void setLine(final String line) {
		this.line = line;
	}

	public ModelTable getModel() {
		return model;
	}

	public void setModel(final ModelTable model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
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

}
