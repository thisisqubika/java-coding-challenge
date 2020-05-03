package com.mooveit.cars.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EngineDTO {

	@JacksonXmlProperty(isAttribute = true)
	private Integer power;
	@JacksonXmlProperty(isAttribute = true)
	private String type;
}
