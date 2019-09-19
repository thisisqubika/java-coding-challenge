package com.mooveit.cars.ingestion.ford.xml.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "MODEL")
@XmlAccessorType(XmlAccessType.FIELD)
public class FordModel {

    @XmlAttribute(required = true)
    private String name;

    @XmlAttribute
    private String type;
    @XmlAttribute
    private String line;
    @XmlAttribute
    private Integer from;
    @XmlAttribute
    private Integer to;

    @XmlElement(name = "ENGINE", required = true)
    private FordEngine engine;

    @XmlElement(name = "WHEELS", required = true)
    private FordWheels wheels;

    @XmlElement(name = "SUBMODELS")
    private FordSubModels subModels = new FordSubModels();
}
