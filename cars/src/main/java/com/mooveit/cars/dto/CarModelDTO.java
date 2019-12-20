package com.mooveit.cars.dto;

import com.mooveit.cars.domain.CarModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarModelDTO {

	private boolean success;

	private String message;

	private Iterable<CarModel> cars;
}
