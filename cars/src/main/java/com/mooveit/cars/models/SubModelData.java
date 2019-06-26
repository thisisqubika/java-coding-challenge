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
public class SubModelData {

    private String name;
    private String line;
    private String from;
    private String to;
    private EngineData engine;
    private WheelData wheel;

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public void setLine(String line) {
        this.line = line;
    }

    @XmlAttribute
    public void setFrom(String from) {
        this.from = from;
    }

    @XmlAttribute
    public void setTo(String to) {
        this.to = to;
    }

    @XmlElement(name = "ENGINE")
    public void setEngine(EngineData engine) {
        this.engine = engine;
    }

    @XmlElement(name = "WHEELS")
    public void setWheel(WheelData wheel) {
        this.wheel = wheel;
    }
}
