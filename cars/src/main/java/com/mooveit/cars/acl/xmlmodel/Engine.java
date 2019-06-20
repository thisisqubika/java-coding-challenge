package com.mooveit.cars.acl.xmlmodel;

public class Engine {

    private Integer power;

    private String type;

    protected Engine() {
    }

    public Engine(Integer power, String type) {
        this.power = power;
        this.type = type;
    }

    public Integer getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public com.mooveit.cars.domain.Engine parse(){
        return new com.mooveit.cars.domain.Engine(this.power, this.type);
    }
}
