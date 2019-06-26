package com.mooveit.cars.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlRootElement(name = "MODEL")
public class ModelData {

    private String name;
    private String from;
    private String to;
    private String type;
    private String line;
    private WheelData wheel;
    private EngineData engine;
    private SubModelContainer subModelContainer;

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public void setFrom(String from) {
        this.from = from;
    }

    @XmlAttribute
    public void setTo(String to) {
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

    @XmlElement(name = "WHEELS")
    public void setWheel(WheelData wheel) {
        this.wheel = wheel;
    }

    @XmlElement(name = "ENGINE")
    public void setEngine(EngineData engine) {
        this.engine = engine;
    }

    @XmlElement(name = "SUBMODELS")
    public void setSubModelContainer(SubModelContainer subModelContainer) {
        this.subModelContainer = subModelContainer;
    }
}
