package com.abdhage.rentail.accommodation.exception;


import com.abdhage.rentail.common.exception.BadRequestException;

public class AccommodationNameMustUniqueException extends BadRequestException {
    public AccommodationNameMustUniqueException() {
        super("Accommodation name already taken");
    }
}
