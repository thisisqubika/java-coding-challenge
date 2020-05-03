package com.mooveit.cars.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubModelDTO {

	@JacksonXmlProperty(isAttribute = true)
	private String name;
	@JacksonXmlProperty(isAttribute = true)
	private String from;
	@JacksonXmlProperty(isAttribute = true)
	private String to;
	@JacksonXmlProperty(isAttribute = true)
	private String type;	
	@JacksonXmlProperty(isAttribute = true)
	private String line;
	@JacksonXmlProperty(localName = "ENGINE")
	private EngineDTO engine;
	@JacksonXmlProperty(localName = "WHEELS")
	private WheelDTO wheel;

}
