package com.mooveit.cars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Lucas Arquiel
 *
 */
public class EngineDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long power;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;

	/**
	 * default constructor
	 */
	public EngineDTO() {
		super();
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