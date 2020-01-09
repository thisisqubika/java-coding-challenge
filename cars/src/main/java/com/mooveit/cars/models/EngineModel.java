package com.mooveit.cars.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineModel {
    private Integer power;
    private String type;

    public EngineModel(Integer power, String type) {
        this.power = power;
        this.type = type;
    }
}
