package com.mooveit.cars.api.dtos;

import com.mooveit.cars.domain.Wheels;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class WheelsDto {

    private int size;
    private Wheels.Type type;
}
