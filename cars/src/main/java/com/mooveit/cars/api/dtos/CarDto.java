package com.mooveit.cars.api.dtos;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.time.Year;

@Value
@Builder(toBuilder = true)
public class CarDto {

    private Long id;
    private String brand;
    private String modelName;
    private String type;
    private @Nullable
    String line;
    private @Nullable
    Year productionYearFrom;
    /**
     * When null indicates that a car is still in production
     */
    private @Nullable
    Year productionYearTo;

    private EngineDto engine;
    private WheelsDto wheels;
}
