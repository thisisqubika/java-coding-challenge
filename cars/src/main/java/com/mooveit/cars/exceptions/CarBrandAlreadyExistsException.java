package com.mooveit.cars.exceptions;

import java.text.MessageFormat;

public class CarBrandAlreadyExistsException extends RuntimeException {

	public CarBrandAlreadyExistsException(String name) {
		super(MessageFormat.format("Car brand with name '{0}' already exists!", name));
	}
}
