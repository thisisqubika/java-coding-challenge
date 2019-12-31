package com.mooveit.cars.utils.xmlmodels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "MODEL")
public class ModelXmlDTO {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "from")
    private Long from;

    @JacksonXmlProperty(localName = "to")
    private Long to;

    @JacksonXmlProperty(localName = "type")
    private String type;

    @JacksonXmlProperty(localName = "line")
    private String line;

    @JacksonXmlProperty(localName = "ENGINE")
    private EngineXmlDTO engineXmlDTO;

    @JacksonXmlProperty(localName = "WHEELS")
    private WheelXmlDTO wheelXmlDTO;

    @JacksonXmlProperty(localName = "MODEL")
    @JacksonXmlElementWrapper(localName = "SUBMODELS")
    private List<ModelXmlDTO> subModels;
}
