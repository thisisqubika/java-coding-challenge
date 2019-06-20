package com.mooveit.cars.acl.xmlmodel;

public class Wheel {

    private String size;

    private String type;


    protected Wheel() {
    }

    public Wheel(String size, String type) {
        this.size = size;
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public com.mooveit.cars.domain.Wheel parse(){
        return new com.mooveit.cars.domain.Wheel(this.size, this.type);
    }
}
