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
@Entity(name = "wheels")
public class WheelsTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String size;

	private String type;

	/**
	 * default constructor
	 */
	protected WheelsTable() {
		// won't be used
	}

	/**
	 * constructor without id
	 * 
	 * @param size
	 * @param type
	 */
	public WheelsTable(final String size, final String type) {
		super();
		this.size = size;
		this.type = type;
	}

	/**
	 * constructor
	 * 
	 * @param id
	 * @param size
	 * @param type
	 */
	public WheelsTable(final Long id, final String size, final String type) {
		super();
		this.id = id;
		this.size = size;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(final String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
