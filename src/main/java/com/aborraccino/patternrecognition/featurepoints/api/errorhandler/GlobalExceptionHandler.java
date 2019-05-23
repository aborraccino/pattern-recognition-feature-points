package com.aborraccino.patternrecognition.featurepoints.api.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aborraccino.patternrecognition.featurepoints.exception.InvalidInputException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> resourceNotFound(InvalidInputException ex) {
		return buildError(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> genericError(RuntimeException ex) {
		return buildError(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> genericError(HttpMessageNotReadableException ex) {
		return buildError(ex, HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<ErrorResponse> buildError(RuntimeException ex, HttpStatus internalServerError) {
		ErrorResponse response = new ErrorResponse();
        response.setCode("99");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, internalServerError);
	}

	private ResponseEntity<ErrorResponse> buildError(InvalidInputException ex, HttpStatus httpStatus) {
		ErrorResponse response = new ErrorResponse();
        response.setCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}
