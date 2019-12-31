package com.mooveit.cars.domain.projections;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.enums.EngineTypeEnum;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "engineProjection", types = {Engine.class})
public interface EngineProjection {
    Long getPower();

    EngineTypeEnum getType();
}
