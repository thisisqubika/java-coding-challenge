package com.mooveit.cars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Lucas Arquiel
 *
 */
public class WheelsDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String size;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;

	/**
	 * default constructor
	 */
	public WheelsDTO() {
		super();
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
