package com.mooveit.cars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "CATALOGUE")
public class Catalogue implements Serializable {
 
    private static final long serialVersionUID = 1L;   
    
    @XmlElement(name = "MODEL")
    private ArrayList<Car> models;
     
    public void setModelsList(ArrayList < Car > modelsList) {
        this.models = modelsList;
    }

    public List < Car > getModelsList() {
        return models;
    }

	public Catalogue() {
        super();
    }
 
    public Catalogue(ArrayList<Car> models) {
        super();
        
        this.models = models;
    }
 
    //Setters and Getters
 
    @Override
    public String toString() {
        return "Catalogue [number of models =" + models.size() + "]";
    }
}