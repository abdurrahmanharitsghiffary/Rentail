package com.abdhage.rentail.exception;

import com.abdhage.rentail.exception.common.BadRequestException;

public class KosCollaboratorMustUniqueException extends BadRequestException {
    public KosCollaboratorMustUniqueException() {
        super("Kos Collaborator already exists");
    }
}
