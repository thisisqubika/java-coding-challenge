package com.mooveit.cars.rest.dto;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.domain.Wheels;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;


@Data
public class CarSpecification extends ResourceSupport {
    private Long idCarSpec;
    private String  name;
    private Integer yearFrom;
    private Integer yearTo;
    private Engine engine;
    private Wheels wheels;
    private String type;
    private String line;

    public CarSpecification(Model model) {
        this.idCarSpec =model.getId();
        this.name=model.getName();
        this.yearFrom=model.getYearFrom();
        this.yearTo=model.getYearTo();
        this.engine=model.getEngine();
        this.wheels=model.getWheels();
        this.type=model.getType();
    }

    public CarSpecification(SubModel subModel) {
        this.idCarSpec =subModel.getId();
        this.name=subModel.getName();
        this.yearFrom=subModel.getYearFrom();
        this.yearTo=subModel.getYearTo();
        this.engine=subModel.getEngine();
        this.wheels=subModel.getWheels();
        this.line=subModel.getLine();
    }
}
