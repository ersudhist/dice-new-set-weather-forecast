package com.weather.service;

public interface WeatherService {
	
	public String getForecastSummaryByLocationName(String cityName);
	public String getHourlyForecastByLocationName(String cityName);
	
}
