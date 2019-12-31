package com.mooveit.cars.utils.xmlmodels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "ENGINE")
public class EngineXmlDTO {

    @JacksonXmlProperty(localName = "power")
    private Long power;

    @JacksonXmlProperty(localName = "type")
    private String type;
}
