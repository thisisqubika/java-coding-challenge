package com.mooveit.cars.tasks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "SUBMODELS")
public class SubModel {

    private List<Model> models;

    @XmlElement(name = "MODEL")
    public void setModels(List<Model> model) {
        this.models = model;
    }

}
