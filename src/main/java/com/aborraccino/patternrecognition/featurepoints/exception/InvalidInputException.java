package com.aborraccino.patternrecognition.featurepoints.exception;

public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public InvalidInputException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public InvalidInputException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public InvalidInputException(Throwable cause, String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public InvalidInputException() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
