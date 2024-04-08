package com.weather.controller;

import org.springframework.web.bind.annotation.RestController;

import com.weather.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("*")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/forecast-summary")
	public ResponseEntity<String> getForecastSummaryByLocationName(@RequestParam String cityName) {

		return new ResponseEntity<String>(weatherService.getForecastSummaryByLocationName(cityName), HttpStatus.ACCEPTED);

	}

	@GetMapping("/hourly-forecast")
	public ResponseEntity<String> getHourlyForecastByLocationName(@RequestParam String cityName) {

		return new ResponseEntity<String>(weatherService.getHourlyForecastByLocationName(cityName), HttpStatus.ACCEPTED);

	}
}

