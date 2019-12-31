package com.mooveit.cars.domain.projections;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.enums.ModelLineEnum;
import com.mooveit.cars.domain.enums.ModelTypeEnum;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "modelFullProjection", types = {Model.class})
public interface ModelFullProjection {
    Long getId();

    String getName();

    Date getFrom();

    Date getTo();

    ModelTypeEnum getType();

    ModelLineEnum getLine();

    EngineProjection getEngine();

    WheelProjection getWheel();

    BrandProjection getBrand();
}
