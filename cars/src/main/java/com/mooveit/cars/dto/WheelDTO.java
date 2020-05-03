package com.mooveit.cars.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WheelDTO {

	@JacksonXmlProperty(isAttribute = true)
	private String size;
	@JacksonXmlProperty(isAttribute = true)
	private String type;
	
}
