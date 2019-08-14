package com.mooveit.cars.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author Lucas Arquiel
 *
 */
public class ModelListDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ModelDTO> models;

	/**
	 * default constructor
	 */
	public ModelListDTO() {
		super();
	}

	@JsonProperty("models")
	public List<ModelDTO> getModelResponseList() {
		return models;
	}

	public void setModelResponseList(final List<ModelDTO> models) {
		this.models = models;
	}

}
