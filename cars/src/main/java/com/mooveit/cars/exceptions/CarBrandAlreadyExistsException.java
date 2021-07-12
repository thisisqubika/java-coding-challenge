package com.mooveit.cars.exceptions;

public class CarBrandAlreadyExistsException extends RuntimeException {

	public CarBrandAlreadyExistsException(String name) {
		super(String.format("Car brand with name '%s' already exists!", name));
	}
}
