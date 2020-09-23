package com.sapient.sapientweatherreport.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConfig
{
    @Bean
    public ObjectMapper getObjectMapper()
    {
        return new ObjectMapper();
    }
}
