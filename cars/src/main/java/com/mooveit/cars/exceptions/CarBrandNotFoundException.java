package com.mooveit.cars.exceptions;

public class CarBrandNotFoundException extends RuntimeException {

	public CarBrandNotFoundException(String name) {
		super(String.format("Car brand with name '%s' not found!", name));
	}
}
