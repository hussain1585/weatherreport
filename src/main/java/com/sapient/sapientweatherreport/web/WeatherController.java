package com.sapient.sapientweatherreport.web;

import com.sapient.sapientweatherreport.domain.WeatherModelResponse;
import com.sapient.sapientweatherreport.exception.CityNotFoundException;
import com.sapient.sapientweatherreport.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * @author Hussain Akhtar Wahid - Sept-23-2020
 */

@Controller
@RequestMapping("/")
public class WeatherController
{
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    WeatherService weatherService;

    @Value("${spring.application.name}")
    private String APP_NAME;

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public String getWeather(Model model, String city_name/*, String city_name_2*/)
    {
        logger.debug("calling weather page");
        logger.info("APP_NAME =>" + APP_NAME);

        WeatherModelResponse weatherModelResponse = null;
        try
        {
            weatherModelResponse = weatherService.getWeatherResponse(city_name);
        } catch (SocketTimeoutException e)
        {
            logger.error("Error : Socket Time Out for Weather API Call");
            model.addAttribute("message","Error : Socket Time Out for Weather API Call");
            return "error";
        } catch (IOException e)
        {
            logger.error("Error : error in API Call");
            e.printStackTrace();
        } catch (CityNotFoundException e)
        {
            logger.error("Error : city not found");
            model.addAttribute("message","Error : city not found");
            return "error";
        }
        model.addAttribute("weatherModelResponse", weatherModelResponse);

        return "weather";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String errorPage(Model model)
    {
        logger.debug("calling error page");
        return "error";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model)
    {
        logger.debug("calling home page");
        return "home";
    }
}
