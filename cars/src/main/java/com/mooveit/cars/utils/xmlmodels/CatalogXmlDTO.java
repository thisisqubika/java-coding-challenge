package com.mooveit.cars.utils.xmlmodels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "CATALOGUE")
public class CatalogXmlDTO {
    @JacksonXmlProperty(localName = "MODEL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ModelXmlDTO> models;
}
