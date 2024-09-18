package com.abdhage.rentail.features.auth.exception;

import com.abdhage.rentail.common.exception.BadRequestException;

public class EmailMustUniqueException extends BadRequestException {
    public EmailMustUniqueException() {
        super("Email already taken");
    }
}
