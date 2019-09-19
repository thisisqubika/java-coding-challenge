package com.mooveit.cars.ingestion.ford.xml.mappers;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.ingestion.ford.xml.model.FordEngine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EngineMapper {

    Engine fordEngineToEngine(FordEngine fordEngine);
}
