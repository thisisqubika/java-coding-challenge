package com.mooveit.cars.xmlDomain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "WHEELS")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlWheels {
    @XmlAttribute
    private String size;
    @XmlAttribute
    private String type;
}
