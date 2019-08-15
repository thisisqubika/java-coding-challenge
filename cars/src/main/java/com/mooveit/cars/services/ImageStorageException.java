package com.mooveit.cars.services;

public class ImageStorageException extends RuntimeException {

	public ImageStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImageStorageException(String message) {
		super(message);
	}

}
