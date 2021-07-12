package com.mooveit.cars.exceptions;

public class ModelNotFoundException extends RuntimeException {

	public ModelNotFoundException(Long id) {
		super(String.format("Model with ID '%d' not found!", id));
	}
}
