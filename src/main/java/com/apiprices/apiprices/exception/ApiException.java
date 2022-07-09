package com.apiprices.apiprices.exception;

public class ApiException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String description;
  private String code = "internal_error";
  private Integer statusCode = 500;

  public ApiException() {
    this("internal_error", "Internal server error");
  }

  public ApiException(String code, String description) {
    super(description);
    this.code = code;
    this.description = description;
  }

}
