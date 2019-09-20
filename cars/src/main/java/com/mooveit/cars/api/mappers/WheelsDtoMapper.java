package com.mooveit.cars.api.mappers;

import com.mooveit.cars.api.dtos.WheelsDto;
import com.mooveit.cars.domain.Wheels;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WheelsDtoMapper {

    WheelsDto wheelsToWheelsDto(Wheels wheels);
}
