package com.abdhage.rentail.common.exception;

import org.springframework.http.HttpStatus;

import java.util.HashSet;

public class HttpException extends BaseException {

    public HttpException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public HttpException(HttpStatus status, String reason, HashSet<Object> errors) {
        super(status, reason, errors);
    }
}
