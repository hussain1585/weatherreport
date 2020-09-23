package com.sapient.sapientweatherreport.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.sapientweatherreport.domain.WeatherModelResponse;
import com.sapient.sapientweatherreport.domain.weather.List;
import com.sapient.sapientweatherreport.domain.weather.WeatherResponse;
import com.sapient.sapientweatherreport.exception.CityNotFoundException;
import com.sapient.sapientweatherreport.util.BaseUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Hussain Akhtar Wahid - Sept-23-2020
 */

@Service
public class WeatherService
{
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Value("${spring.weather.app.key}")
    private String APP_KEY;

    @Value("${spring.app.base.url}")
    private String BASE_URL;

    @Autowired
    private ObjectMapper objectMapper;

    public WeatherModelResponse getWeatherResponse(String city_name) throws IOException, CityNotFoundException, SocketTimeoutException
    {
        logger.debug("get weather response");
        if (StringUtils.isEmpty(city_name))
        {
            logger.debug("city_name is empty rolling back to default city");
            city_name = "London,UK";
        }
        String response_json_string = "";

        String url = BASE_URL + "?q=" + city_name + "&appid=" + APP_KEY;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        Response response = null;
        response = call.execute();
        response_json_string = response.body().string();

        logger.debug("response" + response.message());
        logger.debug("code" + response.code());

        WeatherResponse weatherResponse = null;
        WeatherModelResponse weatherModelResponse = null;

        if (response.code() == HttpStatus.NOT_FOUND.value())
        {
            logger.debug("City Not Found");
            throw new CityNotFoundException("City Not Found");
        } else
        {
            logger.debug("response_json_string => "+response_json_string);

            weatherResponse = objectMapper.readValue(response_json_string, WeatherResponse.class);

            Double tempMax = getMaxTemp(weatherResponse);
            Double tempMin = getMinTemp(weatherResponse);

            boolean rainPredictedInNext3Days = isRainPredictedInNext3Days(weatherResponse);
            boolean temp_over_40_prediction = doesTemperatureGoBeyond40DegreeCelsius(weatherResponse);

            java.util.List<String> rainTimeWindow = fetchTimeWindowsForRain(weatherResponse);
            java.util.List<String> highTempWindow = fetchTimeWindowsTemperatureHigherThan40C(weatherResponse);

            weatherModelResponse = new WeatherModelResponse();
            weatherModelResponse.setTempMax(tempMax);
            weatherModelResponse.setTempMin(tempMin);
            weatherModelResponse.setRainPredictedInNext3Days(rainPredictedInNext3Days);
            weatherModelResponse.setDoesTemperatureGoBeyond40DegreeCelsius(temp_over_40_prediction);
            weatherModelResponse.setRainTimeWindow(rainTimeWindow);
            weatherModelResponse.setHighTempWindow(highTempWindow);
            weatherModelResponse.setCity(city_name);
        }
        return weatherModelResponse;
    }

    private boolean doesTemperatureGoBeyond40DegreeCelsius(WeatherResponse weatherResponse)
    {
        for (List list : weatherResponse.getList())
        {
            if (BaseUtil.convertKelvinToCelsius(list.getMain().getTempMax()) > 40)
            {
                return true;
            }
        }
        return false;
    }

    private boolean isRainPredictedInNext3Days(WeatherResponse weatherResponse)
    {
        for (List list : weatherResponse.getList())
        {
            if ((null != list.getRain()) && (null != list.getRain().get3h()) && (list.getRain().get3h()) > 0)
            {
                return true;
            }
        }
        return false;
    }

    private java.util.List<String> fetchTimeWindowsForRain(WeatherResponse weatherResponse)
    {
        for (List list : weatherResponse.getList())
        {
            if ((null != list.getRain()) && (null != list.getRain().get3h()) && (list.getRain().get3h()) > 0)
            {
                return fetchTimeWindows(list);
            }
        }
        return null;
    }

    private java.util.List<String> fetchTimeWindows(List list)
    {
        java.util.List<String> time_window_list = new ArrayList<>();
        long epoch = list.getDt();
        Date start = new Date(epoch * 1000);
        Date end = new Date((epoch + 4 * 60 * 60) * 1000);
        time_window_list.add(start + " - " + end);

        return time_window_list;
    }

    private java.util.List<String> fetchTimeWindowsTemperatureHigherThan40C(WeatherResponse weatherResponse)
    {
        for (List list : weatherResponse.getList())
        {
            if (BaseUtil.convertKelvinToCelsius(list.getMain().getTempMax()) > 40)
            {
                return fetchTimeWindows(list);
            }
        }
        return null;
    }

    public Double getMaxTemp(WeatherResponse weatherResponse)
    {
        Double maxTemperature = weatherResponse.getList().get(0).getMain().getTempMax();

        for (List list : weatherResponse.getList())
        {
            Double tempMaxTemp = list.getMain().getTempMax();
            maxTemperature = tempMaxTemp > maxTemperature ? tempMaxTemp : maxTemperature;
        }

        return BaseUtil.convertKelvinToCelsius(maxTemperature);
    }

    public Double getMinTemp(WeatherResponse weatherResponse)
    {
        Double minTemperature = weatherResponse.getList().get(0).getMain().getTempMin();

        for (List list : weatherResponse.getList())
        {
            Double tempMinTemp = list.getMain().getTempMin();
            minTemperature = tempMinTemp < minTemperature ? tempMinTemp : minTemperature;
        }

        return BaseUtil.convertKelvinToCelsius(minTemperature);
    }
}