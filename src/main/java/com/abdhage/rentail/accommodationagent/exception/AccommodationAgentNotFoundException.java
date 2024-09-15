package com.abdhage.rentail.accommodationagent.exception;

import com.abdhage.rentail.common.exception.NotFoundException;

public class AccommodationAgentNotFoundException extends NotFoundException {
    public AccommodationAgentNotFoundException() {
        super("Accommodation Agent not found");
    }
}
