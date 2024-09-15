package com.goshier.kopos.exception;

import com.goshier.kopos.exception.common.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
