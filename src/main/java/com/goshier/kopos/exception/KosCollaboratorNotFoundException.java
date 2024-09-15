package com.goshier.kopos.exception;

import com.goshier.kopos.exception.common.NotFoundException;

public class KosCollaboratorNotFoundException extends NotFoundException {
    public KosCollaboratorNotFoundException() {
        super("Kos Collaborator not found");
    }
}
