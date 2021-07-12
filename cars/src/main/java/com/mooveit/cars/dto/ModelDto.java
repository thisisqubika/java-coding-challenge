package com.mooveit.cars.dto;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;

@Data
@XStreamAlias("MODEL")
public class ModelDto implements Serializable {
	private static final long serialVersionUID = -7702483387617011359L;
	@XStreamOmitField
	private Long id;
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String from;
	@XStreamAsAttribute
	private String to;
	@XStreamAsAttribute
	private String type;
	@XStreamAsAttribute
	private String line;
	@XStreamAlias("ENGINE")
	private EngineDto engineDto;
	@XStreamAlias("WHEELS")
	private WheelsDto wheelsDto;
	@XStreamAlias("SUBMODELS")
	private SubModelsDto subModelsDto;

	public ModelDto() {
	}

	@Override
	public String toString() {
		return "ModelDto{" + "id=" + id + ", name='" + name + '\'' + ", from='" + from + '\'' + ", to='" + to + '\'' + ", type='" + type + '\'' + ", line='" + line + '\'' + ", engineDto=" + engineDto + ", wheelsDto=" + wheelsDto + ", subModelsDto=" + subModelsDto + '}';
	}
}
