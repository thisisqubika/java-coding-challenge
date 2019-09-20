package com.mooveit.cars.api.mappers;

import com.mooveit.cars.api.dtos.EngineDto;
import com.mooveit.cars.domain.Engine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EngineDtoMapper {

    EngineDto engineToEngineDto(Engine engine);
}
