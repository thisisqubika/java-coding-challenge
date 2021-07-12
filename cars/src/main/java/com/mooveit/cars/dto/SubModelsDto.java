package com.mooveit.cars.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;

@Data
@XStreamAlias("SUBMODELS")
public class SubModelsDto implements Serializable {
	private static final long serialVersionUID = -6491157741337175370L;
	@XStreamOmitField
	private Long id;
	@XStreamImplicit(itemFieldName = "MODEL")
	private List<ModelDto> modelsDtos = new ArrayList<>();

	public SubModelsDto() {
	}

	public SubModelsDto(List<ModelDto> modelsDtos) {
		this.modelsDtos = modelsDtos;
	}

	@Override
	public String toString() {
		return "SubModelsDto{" + "id=" + id + ", modelsDtos=" + modelsDtos + '}';
	}
}
