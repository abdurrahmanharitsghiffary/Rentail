package com.goshier.kopos.exception;


import com.goshier.kopos.exception.common.BadRequestException;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException() {
        super("Username or email already taken");
    }
}
