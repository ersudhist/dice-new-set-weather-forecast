package com.weather.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.exception.WeatherException;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService{
	
	@Value("${X-RapidAPI-Key}")
	private String rapidkey;

	@Value("${X-RapidAPI-Host}")
	private String rapidhost;
	
	@Value("${open-Key}")
	private String apikey;
	
	private static final String Summary_URL = "";
    private static final String  Hourly_URL=""; 
	   
    RestTemplate restTemplate=new RestTemplate();

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	

	@Override
	public String getForecastSummaryByLocationName(String cityName) {
        String url = Summary_URL + cityName + "/summary/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidkey);
        headers.set("X-RapidAPI-Host", rapidhost);
        headers.set("Client-ID", "client-" + UUID.randomUUID().toString().substring(0, 8));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve weather summary for city: " + cityName;
            logger.error(errorMessage, e);
            throw new WeatherException(errorMessage, e);
        }
	}

	@Override
	public String getHourlyForecastByLocationName(String cityName) {
        String url = Hourly_URL + "?q=" + cityName + "&appid=" + apikey;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", "client-" + UUID.randomUUID().toString().substring(0, 8));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve hourly weather forecast for city: " + cityName;
            logger.error(errorMessage, e);
            throw new WeatherException(errorMessage, e);
        }
	}

}
