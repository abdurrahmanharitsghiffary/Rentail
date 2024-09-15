package com.abdhage.rentail.exception;

import com.abdhage.rentail.exception.common.BadRequestException;

public class InvalidCredentialsException extends BadRequestException {
    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }
}
