package com.mooveit.cars.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;

@Data
@XStreamAlias("CATALOGUE")
public class CatalogueDto implements Serializable {
	private static final long serialVersionUID = 8286966866687089758L;
	@XStreamOmitField
	private Long id;
	@XStreamImplicit(itemFieldName = "MODEL")
	private List<ModelDto> modelsDtos = new ArrayList<>();

	public CatalogueDto() {
	}

	public CatalogueDto(List<ModelDto> modelsDtos) {
		this.modelsDtos = modelsDtos;
	}

	@Override
	public String toString() {
		return "CatalogueDto{" + "id=" + id + ", modelsDtos=" + modelsDtos + '}';
	}
}
