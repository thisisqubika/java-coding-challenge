package com.mooveit.cars.tasks.model;

import com.mooveit.cars.domain.ford.types.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ENGINE")
public class Engine {

    private Integer power;

    private EngineType type;

    @XmlAttribute
    public void setPower(Integer power) {
        this.power = power;
    }

    @XmlAttribute
    public void setType(EngineType type) {
        this.type = type;
    }
}
