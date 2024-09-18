package com.abdhage.rentail.features.user.exception;

import com.abdhage.rentail.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
