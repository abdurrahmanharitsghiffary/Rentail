package com.abdhage.rentail.exception;

import com.abdhage.rentail.exception.common.NotFoundException;

public class KosCollaboratorNotFoundException extends NotFoundException {
    public KosCollaboratorNotFoundException() {
        super("Kos Collaborator not found");
    }
}
