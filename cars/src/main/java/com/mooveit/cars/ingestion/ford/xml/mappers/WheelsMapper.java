package com.mooveit.cars.ingestion.ford.xml.mappers;

import com.mooveit.cars.domain.Wheels;
import com.mooveit.cars.ingestion.ford.xml.model.FordWheels;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface WheelsMapper {

    @Named("fordSizeToInt")
    static int fordSizeToInt(String size) {
        return Integer.parseInt(size.replaceFirst("R", ""));
    }

    @Mapping(source = "size", target = "size", qualifiedByName = "fordSizeToInt")
    Wheels fordWheelsToWheels(FordWheels fordWheels);
}
