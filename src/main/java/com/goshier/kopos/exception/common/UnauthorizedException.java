package com.goshier.kopos.exception.common;

import org.springframework.http.HttpStatus;

import java.util.HashSet;

public class UnauthorizedException extends BaseException {

    private static final String defaultMessage = "Unauthorized";
    
    public UnauthorizedException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, defaultMessage);
    }

    public UnauthorizedException(String reason, HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, reason, errors);
    }

    public UnauthorizedException(HashSet<Object> errors) {
        super(HttpStatus.BAD_REQUEST, defaultMessage, errors);
    }
}
