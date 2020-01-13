package com.mooveit.cars.exceptions;

public class ItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(Long id) {
		super("Id not found : " + id);
	}
	
	public ItemNotFoundException(String name) {
		super("Model Brand not found : " + name);
	}
}
