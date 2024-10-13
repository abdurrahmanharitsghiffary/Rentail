package com.abdhage.rentail.user.exception;


import com.abdhage.rentail.common.exception.BadRequestException;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException() {
        super("Username or email already taken");
    }
}
