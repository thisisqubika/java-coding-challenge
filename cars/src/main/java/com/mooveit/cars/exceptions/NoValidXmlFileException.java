package com.mooveit.cars.exceptions;

public class NoValidXmlFileException extends RuntimeException {
    public NoValidXmlFileException(String message) {
        super(message);
    }

    public NoValidXmlFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
