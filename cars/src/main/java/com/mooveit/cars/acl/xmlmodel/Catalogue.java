package com.mooveit.cars.acl.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Catalogue {

    @JacksonXmlProperty(localName = "MODEL")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Model> models;

    public Catalogue(List<Model> models) {
        this.models = models;
    }

    protected Catalogue() {
    }

    public List<Model> getModels() {
        return models;
    }
}
