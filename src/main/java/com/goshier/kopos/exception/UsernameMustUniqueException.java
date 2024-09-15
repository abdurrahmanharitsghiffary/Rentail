package com.goshier.kopos.exception;

import com.goshier.kopos.exception.common.BadRequestException;

public class UsernameMustUniqueException extends BadRequestException {
    public UsernameMustUniqueException() {
        super("Username already taken");
    }
}
