package com.mooveit.cars.ingestion.ford.xml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "ENGINE")
@XmlAccessorType(XmlAccessType.FIELD)
public class FordEngine {

    @XmlAttribute(required = true)
    private int power;

    @XmlAttribute(required = true)
    private String type;
}
