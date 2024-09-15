package com.abdhage.rentail.common.exception;

import org.springframework.http.HttpStatus;

import java.util.HashSet;

public class ForbiddenException extends BaseException {

    private static final String defaultMessage = "Forbidden Resource";

    public ForbiddenException(String reason) {
        super(HttpStatus.FORBIDDEN, reason);
    }

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, defaultMessage);
    }

    public ForbiddenException(String reason, HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, reason, errors);
    }

    public ForbiddenException(HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, defaultMessage, errors);
    }
}
