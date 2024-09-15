package com.abdhage.rentail.exception;

import com.abdhage.rentail.exception.common.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
