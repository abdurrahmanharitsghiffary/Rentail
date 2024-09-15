package com.goshier.kopos.exception.common;

import org.springframework.http.HttpStatus;

import java.util.HashSet;

public class NotFoundException extends BaseException {

    private static final String defaultMessage = "Resource not found";

    public NotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, defaultMessage);
    }

    public NotFoundException(String reason, HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, reason, errors);
    }

    public NotFoundException(HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, defaultMessage, errors);
    }
}
