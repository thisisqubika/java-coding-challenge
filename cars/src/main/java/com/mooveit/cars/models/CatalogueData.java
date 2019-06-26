package com.mooveit.cars.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlRootElement(name = "CATALOGUE")
public class CatalogueData {

    private List<ModelData> model;

    @XmlElement(name = "MODEL")
    public void setModel(List<ModelData> model) {
        this.model = model;
    }
}
