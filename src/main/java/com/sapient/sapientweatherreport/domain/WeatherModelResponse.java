package com.sapient.sapientweatherreport.domain;

import java.util.List;

public class WeatherModelResponse
{
    private String city;
    private Double tempMax;
    private Double tempMin;
    private boolean isRainPredictedInNext3Days;
    private boolean doesTemperatureGoBeyond40DegreeCelsius;
    private List<String> rainTimeWindow;
    private List<String> highTempWindow;

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Double getTempMax()
    {
        return tempMax;
    }

    public void setTempMax(Double tempMax)
    {
        this.tempMax = tempMax;
    }

    public Double getTempMin()
    {
        return tempMin;
    }

    public void setTempMin(Double tempMin)
    {
        this.tempMin = tempMin;
    }

    public boolean isRainPredictedInNext3Days()
    {
        return isRainPredictedInNext3Days;
    }

    public void setRainPredictedInNext3Days(boolean rainPredictedInNext3Days)
    {
        isRainPredictedInNext3Days = rainPredictedInNext3Days;
    }

    public boolean isDoesTemperatureGoBeyond40DegreeCelsius()
    {
        return doesTemperatureGoBeyond40DegreeCelsius;
    }

    public void setDoesTemperatureGoBeyond40DegreeCelsius(boolean doesTemperatureGoBeyond40DegreeCelsius)
    {
        this.doesTemperatureGoBeyond40DegreeCelsius = doesTemperatureGoBeyond40DegreeCelsius;
    }

    public List<String> getRainTimeWindow()
    {
        return rainTimeWindow;
    }

    public void setRainTimeWindow(List<String> rainTimeWindow)
    {
        this.rainTimeWindow = rainTimeWindow;
    }

    public List<String> getHighTempWindow()
    {
        return highTempWindow;
    }

    public void setHighTempWindow(List<String> highTempWindow)
    {
        this.highTempWindow = highTempWindow;
    }
}
