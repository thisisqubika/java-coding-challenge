package com.mooveit.cars.dto;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ModelDTO {

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
	
	@JacksonXmlProperty(localName = "SUBMODELS")
    @JacksonXmlElementWrapper(useWrapping = true)
	private List<SubModelDTO> subModels;
	
}
