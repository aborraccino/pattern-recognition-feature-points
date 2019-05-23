package com.aborraccino.patternrecognition.featurepoints.api.errorhandler;

public class ErrorResponse   {

  private String code = null;
  private String message = null;

  public ErrorResponse code(String code) {
    this.code = code;
    return this;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

