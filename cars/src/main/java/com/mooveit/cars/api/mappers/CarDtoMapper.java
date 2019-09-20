package com.mooveit.cars.api.mappers;

import com.mooveit.cars.api.dtos.CarDto;
import com.mooveit.cars.domain.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarDtoMapper {

    CarDto carToCarDto(Car car);
}
