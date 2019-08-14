package com.mooveit.cars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Lucas Arquiel
 *
 */
public class ModelDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String brand;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("from")
	private Long year_from;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("to")
	private Long year_to;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private SubmodelsDTO submodels;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private EngineDTO engine;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private WheelsDTO wheels;

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

	public SubmodelsDTO getSubmodels() {
		return submodels;
	}

	public void setSubmodels(final SubmodelsDTO submodels) {
		this.submodels = submodels;
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

	public EngineDTO getEngine() {
		return engine;
	}

	public void setEngine(final EngineDTO engine) {
		this.engine = engine;
	}

	public WheelsDTO getWheels() {
		return wheels;
	}

	public void setWheels(final WheelsDTO wheels) {
		this.wheels = wheels;
	}

}
