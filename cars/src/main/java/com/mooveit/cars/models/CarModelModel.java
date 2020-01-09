package com.mooveit.cars.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CarModelModel extends RepresentationModel<CarModelModel> {
    private Long id;
    private String name;
    private String brand;
    private String parentName;
    private Integer from;
    private Integer to;
    private String type;
    private String line;
    private WheelModel wheel;
    private EngineModel engine;
}
