package com.abdhage.rentail.features.accommodation.exception;

import com.abdhage.rentail.common.exception.NotFoundException;

public class AccommodationNotFoundException extends NotFoundException {
    public AccommodationNotFoundException() {
        super("Accommodation not found.");
    }
}
