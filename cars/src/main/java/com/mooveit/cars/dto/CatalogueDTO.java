package com.mooveit.cars.dto;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "CATALOGUE")
public class CatalogueDTO {

	@JacksonXmlProperty(localName = "MODEL")
    @JacksonXmlElementWrapper(useWrapping = false)
	private List<ModelDTO> model;
}
