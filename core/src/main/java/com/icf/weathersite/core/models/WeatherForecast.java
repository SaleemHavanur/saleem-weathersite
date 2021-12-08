package com.icf.weathersite.core.models;

public interface WeatherForecast {

    String getCurrentTemperature();

    String getSunriseTime();
    String getSunsetTime();

    String getMaximumTemperature();
    String getMinimumTemperature();
    String getHumidity();

}
