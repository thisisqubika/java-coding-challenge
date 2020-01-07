package com.mooveit.cars.tasks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "MODEL")
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    private String name;

    private int from;

    private int to;

    private String type;

    private String line;

    private Engine engine;

    private Wheels wheels;

    private SubModel subModels;

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public void setFrom(int from) {
        this.from = from;
    }

    @XmlAttribute
    public void setTo(int to) {
        this.to = to;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute
    public void setLine(String line) {
        this.line = line;
    }

    @XmlElement(name = "ENGINE")
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @XmlElement(name = "WHEELS")
    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    @XmlElement(name = "SUBMODELS")
    public void setSubModelList(SubModel subModels) {
        this.subModels = subModels;
    }
}
