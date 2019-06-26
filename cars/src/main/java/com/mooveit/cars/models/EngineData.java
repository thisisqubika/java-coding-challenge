package com.mooveit.cars.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlRootElement(name = "ENGINE")
public class EngineData {

    private String power;
    private String type;

    @XmlAttribute
    public void setPower(String power) {
        this.power = power;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }
}
