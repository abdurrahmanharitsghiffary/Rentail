package com.abdhage.rentail.exception;

import com.abdhage.rentail.exception.common.BadRequestException;

public class EmailMustUniqueException extends BadRequestException {
    public EmailMustUniqueException() {
        super("Email already taken");
    }
}
