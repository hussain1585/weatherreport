package com.sapient.sapientweatherreport.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class WeatherController
{

    @Autowired
    WeatherService weatherService;

    @Value("${spring.application.name}")
    private String app_name;

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public String getWeather(Model model)
    {
        System.out.println("app_name =>"+app_name);
        String code = "";
        WeatherModelResponse weatherModelResponse = null;
        try
        {
            weatherModelResponse = weatherService.getWeatherResponse();
        } catch (IOException e)
        {
            System.out.println("error in api call");
            e.printStackTrace();
        }
        model.addAttribute("weatherModelResponse",weatherModelResponse);

        return "weather";
    }
}
