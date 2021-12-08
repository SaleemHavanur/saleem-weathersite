package com.icf.weathersite.core.service;

import com.icf.weathersite.core.data.ApiResponse;
import com.icf.weathersite.core.dataHourly.ApiHourlyResponse;
import com.icf.weathersite.core.fiveDaysData.ApiResponseFiveDays;


public interface WeatherService {

    ApiResponse getCurrentWeather(String cityName);
    ApiResponseFiveDays getDailyForecastFor5Days(String name);
    ApiHourlyResponse getOneCallApi(double lat, double lon, String exclude);

}
