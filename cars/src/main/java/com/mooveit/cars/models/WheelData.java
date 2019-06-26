package com.mooveit.cars.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlRootElement(name = "WHEEL")
public class WheelData {

    private String size;
    private String type;

    @XmlAttribute
    public void setSize(String size) {
        this.size = size;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }
}
