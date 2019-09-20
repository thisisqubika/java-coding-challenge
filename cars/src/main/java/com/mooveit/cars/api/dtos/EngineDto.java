package com.mooveit.cars.api.dtos;

import com.mooveit.cars.domain.Engine;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EngineDto {

    private int power;
    private Engine.Type type;
}
