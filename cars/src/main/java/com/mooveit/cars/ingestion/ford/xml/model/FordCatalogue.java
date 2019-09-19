package com.mooveit.cars.ingestion.ford.xml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "CATALOGUE")
@XmlAccessorType(XmlAccessType.FIELD)
public class FordCatalogue {

    @XmlElement(name = "MODEL")
    private List<FordModel> fordModels = new ArrayList<>();
}
