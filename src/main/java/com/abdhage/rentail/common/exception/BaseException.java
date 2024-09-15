package com.abdhage.rentail.common.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public abstract class BaseException extends ResponseStatusException {

    private HashSet<Object> errors;

    public BaseException(HttpStatus statusCode, String reason) {
        super(statusCode, reason);
    }


    public BaseException(HttpStatus statusCode, String reason, HashSet<Object> errors) {
        super(statusCode, reason);

        this.errors = errors;
    }
}
