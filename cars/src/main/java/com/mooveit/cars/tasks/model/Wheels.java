package com.mooveit.cars.tasks.model;

import com.mooveit.cars.domain.ford.types.RimSize;
import com.mooveit.cars.domain.ford.types.RimType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "WHEEL")
public class Wheels {

    private RimSize size;

    private RimType type;

    @XmlAttribute
    public void setSize(RimSize size) {
        this.size = size;
    }

    @XmlAttribute
    public void setType(RimType type) {
        this.type = type;
    }


}
