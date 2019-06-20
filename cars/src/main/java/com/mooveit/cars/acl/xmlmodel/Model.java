package com.mooveit.cars.acl.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Model {

    private String name;

    private Integer from;

    private Integer to;

    private String type;

    private String line;

    @JacksonXmlProperty(localName = "SUBMODELS")
    @JacksonXmlElementWrapper()
    private List<Model> submodels;

    @JacksonXmlProperty(localName = "ENGINE")
    private Engine engine;

    @JacksonXmlProperty(localName = "WHEELS")
    private Wheel wheel;

    protected Model() {
    }

    public Model(String name, Integer from, Integer to, String type, String line, List<Model> submodels, Engine engine, Wheel wheel) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.type = type;
        this.line = line;
        this.submodels = submodels;
        this.engine = engine;
        this.wheel = wheel;
    }

    public String getName() {
        return name;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public String getType() {
        return type;
    }

    public String getLine() {
        return line;
    }

    public List<Model> getSubmodels() {
        return submodels;
    }

    public Engine getEngine() {
        return engine;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public com.mooveit.cars.domain.Model parse(com.mooveit.cars.domain.Model parent){
        return new com.mooveit.cars.domain.Model(getName(), getFrom(), getTo(), getType(), getLine(), parent, getEngine().parse(), getWheel().parse());
    }
}
