package com.goshier.kopos.exception;

import com.goshier.kopos.exception.common.BadRequestException;

public class InvalidCredentialsException extends BadRequestException {
    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }
}
