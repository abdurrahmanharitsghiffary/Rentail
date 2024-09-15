package com.goshier.kopos.exception;

import com.goshier.kopos.exception.common.BadRequestException;

public class EmailMustUniqueException extends BadRequestException {
    public EmailMustUniqueException() {
        super("Email already taken");
    }
}
