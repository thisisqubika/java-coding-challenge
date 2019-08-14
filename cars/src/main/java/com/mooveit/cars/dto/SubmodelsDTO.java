package com.mooveit.cars.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author Lucas Arquiel
 *
 */
@JsonRootName(value = "submodels")
public class SubmodelsDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<SubmodelDTO> submodel;

	/**
	 * default constructor
	 */
	public SubmodelsDTO() {
		super();
	}

	public List<SubmodelDTO> getSubmodel() {
		return submodel;
	}

	public void setSubmodel(final List<SubmodelDTO> submodel) {
		this.submodel = submodel;
	}

}
