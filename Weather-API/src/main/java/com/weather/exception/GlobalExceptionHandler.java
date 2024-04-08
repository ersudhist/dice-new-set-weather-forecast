package com.weather.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorFormat> handleException(Exception ex, WebRequest req) {
		logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
		ErrorFormat ef = new ErrorFormat(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ef);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorFormat> NoHandelerExceptionHandler(NoHandlerFoundException ex, WebRequest req ){
		logger.error("Weather service exception occurred: {}", ex.getMessage(), ex);
		ErrorFormat ef = new ErrorFormat(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ef);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorFormat> NotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest req){
		logger.error("Weather service exception occurred: {}", ex.getMessage(), ex);
		ErrorFormat ef = new ErrorFormat(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ef);
	}

	@ExceptionHandler(WeatherException.class)
	public ResponseEntity<ErrorFormat> handleWeatherServiceException(WeatherException ex, WebRequest req) {
		logger.error("Weather service exception occurred: {}", ex.getMessage(), ex);
		ErrorFormat ef = new ErrorFormat(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ef);
	}

}
