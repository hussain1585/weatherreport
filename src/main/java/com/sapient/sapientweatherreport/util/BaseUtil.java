package com.sapient.sapientweatherreport.util;

/**
 * @author Hussain Akhtar Wahid - Sept-23-2020
 */

public class BaseUtil
{
    public static Double convertKelvinToCelsius(Double kelvin)
    {
        return Math.floor(((kelvin - 273.15) * 100)/100);
    }
}
