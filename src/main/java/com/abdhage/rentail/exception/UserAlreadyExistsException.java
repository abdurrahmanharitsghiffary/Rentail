package com.abdhage.rentail.exception;


import com.abdhage.rentail.exception.common.BadRequestException;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException() {
        super("Username or email already taken");
    }
}
