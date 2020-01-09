package com.mooveit.cars.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WheelModel {
    private String size;
    private String type;

    public WheelModel(String size, String type) {
        this.size = size;
        this.type = type;
    }
}
