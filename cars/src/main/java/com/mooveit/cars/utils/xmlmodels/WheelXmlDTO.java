package com.mooveit.cars.utils.xmlmodels;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "MODEL")
public class WheelXmlDTO {

    @JacksonXmlProperty(localName = "size")
    private String size;

    @JacksonXmlProperty(localName = "type")
    private String type;

}
