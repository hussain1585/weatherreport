package com.sapient.sapientweatherreport.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class WeatherService
{
    @Value("${spring.weather.app.key}")
    private String app_key;

//	private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast?q=London,us&appid=d2929e9483efc82c82c32ee7%20e02d563e";

    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast";


    public WeatherModelResponse getWeatherResponse() throws IOException
    {

        String city_name_with_state_code = "London,us";

        String url = BASE_URL + "?q=" +city_name_with_state_code + "&appid=" +app_key ;

        OkHttpClient client = new OkHttpClient();
        System.out.println(" running weather service : getAPI call");
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println("response"+response.message());
        System.out.println("code"+response.code());
        String response_json_string = response.body().string();

        ObjectMapper objectMapper= new ObjectMapper();

        WeatherResponse weatherResponse = objectMapper.readValue(response_json_string, WeatherResponse.class);

        Double tempMax = getMaxTemp(weatherResponse);
        Double tempMin = getMinTemp(weatherResponse);

        boolean rainPredictedInNext3Days = isRainPredictedInNext3Days(weatherResponse);
        boolean temp_over_40_prediction = doesTemperatureGoBeyond40DegreeCelsius(weatherResponse);

        java.util.List<String> rainTimeWindow = fetchTimeWindowsForRain(weatherResponse);
        java.util.List<String> highTempWindow = fetchTimeWindowsTemperatureHigherThan40C(weatherResponse);

        WeatherModelResponse weatherModelResponse = new WeatherModelResponse();
        weatherModelResponse.setTempMax(tempMax);
        weatherModelResponse.setTempMin(tempMin);
        weatherModelResponse.setRainPredictedInNext3Days(rainPredictedInNext3Days);
        weatherModelResponse.setDoesTemperatureGoBeyond40DegreeCelsius(temp_over_40_prediction);
        weatherModelResponse.setRainTimeWindow(rainTimeWindow);
        weatherModelResponse.setHighTempWindow(highTempWindow);
        weatherModelResponse.setCity(city_name_with_state_code);


        return weatherModelResponse;
    }

    private boolean doesTemperatureGoBeyond40DegreeCelsius(WeatherResponse weatherResponse)
    {
        for(List list : weatherResponse.getList())
        {
            if(BaseUtil.convertKelvinToCelsius(list.getMain().getTempMax()) > 40 )
            {
                return true;
            }
        }
        return false;
    }

    private boolean isRainPredictedInNext3Days(WeatherResponse weatherResponse)
    {
        for(List list : weatherResponse.getList())
        {
            if((null != list.getRain()) && (null != list.getRain().get3h()) && (list.getRain().get3h()) > 0)
            {
                return true;
            }
        }
        return false;
    }

    private java.util.List<String> fetchTimeWindowsForRain(WeatherResponse weatherResponse)
    {
        java.util.List<String> rain_time_window = new ArrayList<String>();
        for(List list : weatherResponse.getList())
        {
            if((null != list.getRain()) && (null != list.getRain().get3h()) && (list.getRain().get3h()) > 0)
            {
                rain_time_window.add(list.getDtTxt());
            }
        }
        return rain_time_window;
    }

    private java.util.List<String> fetchTimeWindowsTemperatureHigherThan40C(WeatherResponse weatherResponse)
    {
        java.util.List<String> high_temp_window = new ArrayList<String>();
        for(List list : weatherResponse.getList())
        {
            if(BaseUtil.convertKelvinToCelsius(list.getMain().getTempMax()) > 40 )
            {
                high_temp_window.add(list.getDtTxt());
            }
        }
        return high_temp_window;
    }

    public  Double getMaxTemp(WeatherResponse weatherResponse)
    {
        Double maxTemperature = weatherResponse.getList().get(0).getMain().getTempMax();

        for(List list : weatherResponse.getList())
        {
            Double tempMaxTemp = list.getMain().getTempMax();
            maxTemperature = tempMaxTemp > maxTemperature ? tempMaxTemp : maxTemperature ;
        }

        return maxTemperature;
    }

    public  Double getMinTemp(WeatherResponse weatherResponse)
    {
        Double minTemperature = weatherResponse.getList().get(0).getMain().getTempMin();

        for(List list : weatherResponse.getList())
        {
            Double tempMinTemp = list.getMain().getTempMin();
            minTemperature = tempMinTemp < minTemperature ? tempMinTemp : minTemperature ;
        }

        return minTemperature;
    }
}