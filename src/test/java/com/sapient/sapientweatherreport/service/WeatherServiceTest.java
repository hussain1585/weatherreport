package com.sapient.sapientweatherreport.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.sapientweatherreport.domain.WeatherModelResponse;
import com.sapient.sapientweatherreport.domain.weather.WeatherResponse;
import com.sapient.sapientweatherreport.exception.CityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class WeatherServiceTest
{
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getWeatherResponseLondonUK() throws IOException, CityNotFoundException
    {
        String city_name = "London,UK";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"London,UK\",\"tempMax\":17.0,\"tempMin\":7.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Thu Sep 24 11:30:00 IST 2020 - Thu Sep 24 15:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForAhvazIR() throws IOException, CityNotFoundException
    {
        String city_name = "Ahvaz,IR";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Ahvaz,IR\",\"tempMax\":44.0,\"tempMin\":24.0,\"doesTemperatureGoBeyond40DegreeCelsius\":true,\"rainTimeWindow\":null,\"highTempWindow\":[\"Thu Sep 24 14:30:00 IST 2020 - Thu Sep 24 18:30:00 IST 2020\"],\"rainPredictedInNext3Days\":false}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForDelhiIN() throws IOException, CityNotFoundException
    {
        String city_name = "Delhi,IN";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Delhi,IN\",\"tempMax\":39.0,\"tempMin\":28.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Sat Sep 26 17:30:00 IST 2020 - Sat Sep 26 21:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForKolkataIN() throws IOException, CityNotFoundException
    {
        String city_name = "Kolkata,IN";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Kolkata,IN\",\"tempMax\":33.0,\"tempMin\":26.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Thu Sep 24 08:30:00 IST 2020 - Thu Sep 24 12:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForLucknowIN() throws IOException, CityNotFoundException
    {
        String city_name = "Lucknow,IN";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Lucknow,IN\",\"tempMax\":36.0,\"tempMin\":23.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Thu Sep 24 05:30:00 IST 2020 - Thu Sep 24 09:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForVaranasiIN() throws IOException, CityNotFoundException
    {
        String city_name = "Varanasi,IN";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Varanasi,IN\",\"tempMax\":34.0,\"tempMin\":23.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Thu Sep 24 05:30:00 IST 2020 - Thu Sep 24 09:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForBangaloreIN() throws IOException, CityNotFoundException
    {
        String city_name = "Bangalore,IN";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Bangalore,IN\",\"tempMax\":27.0,\"tempMin\":19.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Thu Sep 24 17:30:00 IST 2020 - Thu Sep 24 21:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForMysoreIN() throws IOException, CityNotFoundException
    {
        String city_name = "Mysore,IN";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Mysore,IN\",\"tempMax\":28.0,\"tempMin\":19.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Thu Sep 24 17:30:00 IST 2020 - Thu Sep 24 21:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForNEWYORKUSA() throws IOException, CityNotFoundException
    {
        String city_name = "NEW YORK,USA";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"NEW YORK,USA\",\"tempMax\":26.0,\"tempMin\":19.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Sat Sep 26 17:30:00 IST 2020 - Sat Sep 26 21:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForParisFR() throws IOException, CityNotFoundException
    {
        String city_name = "Paris,FR";
        WeatherModelResponse weather_response_actual = weatherService.getWeatherResponse(city_name);
        String weather_response_string = "{\"city\":\"Paris,FR\",\"tempMax\":17.0,\"tempMin\":8.0,\"doesTemperatureGoBeyond40DegreeCelsius\":false,\"rainTimeWindow\":[\"Thu Sep 24 05:30:00 IST 2020 - Thu Sep 24 09:30:00 IST 2020\"],\"highTempWindow\":null,\"rainPredictedInNext3Days\":true}";
        WeatherModelResponse weather_response_expected = objectMapper.readValue(weather_response_string,WeatherModelResponse.class);
        assertTrue(weather_response_actual.getCity().equals(city_name));
        assertTrue(weather_response_actual.getCity().equals(weather_response_expected.getCity()));
    }

    @Test
    void getWeatherResponseForNonExistingCity() throws IOException, CityNotFoundException
    {
        String city_name = "asd";
        WeatherModelResponse weather_response_actual = null;
        Exception exception = assertThrows(CityNotFoundException.class, () -> {
            weatherService.getWeatherResponse(city_name);
        });
    }
}