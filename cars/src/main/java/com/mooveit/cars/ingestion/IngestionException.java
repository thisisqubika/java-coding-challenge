package com.mooveit.cars.ingestion;

public class IngestionException extends RuntimeException {

	private static final long serialVersionUID = 612343670266273279L;

	public IngestionException() {
		super();
	}

	public IngestionException(String message, Throwable cause) {
		super(message, cause);
	}

}
