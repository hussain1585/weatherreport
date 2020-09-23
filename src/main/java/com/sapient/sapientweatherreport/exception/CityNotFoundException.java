package com.sapient.sapientweatherreport.exception;

/**
 * @author Hussain Akhtar Wahid - Sept-23-2020
 */

public class CityNotFoundException extends Exception {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}