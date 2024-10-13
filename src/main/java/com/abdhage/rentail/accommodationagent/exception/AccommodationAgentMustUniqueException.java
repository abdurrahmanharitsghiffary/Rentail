package com.abdhage.rentail.accommodationagent.exception;

import com.abdhage.rentail.common.exception.BadRequestException;

public class AccommodationAgentMustUniqueException extends BadRequestException {
    public AccommodationAgentMustUniqueException() {
        super("Accommodation Agent already exists");
    }
}
