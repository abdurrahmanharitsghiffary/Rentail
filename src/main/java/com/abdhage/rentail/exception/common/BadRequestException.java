package com.abdhage.rentail.exception.common;

import org.springframework.http.HttpStatus;

import java.util.HashSet;

public class BadRequestException extends BaseException {

    private static final String defaultMessage = "Bad Server Request";

    public BadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, defaultMessage);
    }

    public BadRequestException(String reason, HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, reason, errors);
    }

    public BadRequestException(HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, defaultMessage, errors);
    }
}
