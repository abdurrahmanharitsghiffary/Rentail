package com.abdhage.rentail.auth.exception;

import com.abdhage.rentail.common.exception.BadRequestException;

public class InvalidCredentialsException extends BadRequestException {
    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }
}
