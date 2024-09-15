package com.abdhage.rentail.exception;

import com.abdhage.rentail.exception.common.NotFoundException;

public class KosNotFoundException extends NotFoundException {
    public KosNotFoundException() {
        super("Kos not found.");
    }
}
