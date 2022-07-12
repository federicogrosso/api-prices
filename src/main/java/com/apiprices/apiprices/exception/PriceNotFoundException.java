package com.apiprices.apiprices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "price not found")
public class PriceNotFoundException extends RuntimeException {

    private final String description;
    private String code = "not_found";

    public PriceNotFoundException(String description, String code) {
        super(description);
        this.code = code;
        this.description = description;
    }
}