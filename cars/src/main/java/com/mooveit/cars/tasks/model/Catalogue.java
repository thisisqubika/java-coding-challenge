package com.mooveit.cars.tasks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement(name = "CATALOGUE")
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue implements CarSpecifications {

    private List<Model> models;

    @XmlElement(name = "MODEL")
    public void setModel(List<Model> model) {
        this.models = model;
    }

    public List<Model> getModel() {
        return models;
    }
}
