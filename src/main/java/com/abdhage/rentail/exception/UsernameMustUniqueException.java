package com.abdhage.rentail.exception;

import com.abdhage.rentail.exception.common.BadRequestException;

public class UsernameMustUniqueException extends BadRequestException {
    public UsernameMustUniqueException() {
        super("Username already taken");
    }
}
