package com.mooveit.cars.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlRootElement(name = "SUBMODELS")
public class SubModelContainer {

    private ArrayList<SubModelData> subModelData;

    @XmlElement(name = "MODEL")
    public void setSubModelData(ArrayList<SubModelData> subModelData) {
        this.subModelData = subModelData;
    }

}
