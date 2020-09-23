package com.sapient.sapientweatherreport.exception;

public class CityNotFoundException extends Exception {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}