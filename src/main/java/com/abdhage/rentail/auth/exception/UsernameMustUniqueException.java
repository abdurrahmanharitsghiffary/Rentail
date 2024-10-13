package com.abdhage.rentail.auth.exception;

import com.abdhage.rentail.common.exception.BadRequestException;

public class UsernameMustUniqueException extends BadRequestException {
    public UsernameMustUniqueException() {
        super("Username already taken");
    }
}
