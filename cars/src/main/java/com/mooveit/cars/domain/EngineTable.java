package com.mooveit.cars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@Entity(name = "engine")
public class EngineTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long power;

	private String type;

	/**
	 * default constructor
	 */
	protected EngineTable() {
		// won't be used
	}

	/**
	 * constructor without id
	 * 
	 * @param power
	 * @param type
	 */
	public EngineTable(final Long power, final String type) {
		super();
		this.power = power;
		this.type = type;
	}

	/**
	 * constructor
	 * 
	 * @param id
	 * @param power
	 * @param type
	 */
	public EngineTable(final Long id, final Long power, final String type) {
		super();
		this.id = id;
		this.power = power;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getPower() {
		return power;
	}

	public void setPower(final Long power) {
		this.power = power;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
