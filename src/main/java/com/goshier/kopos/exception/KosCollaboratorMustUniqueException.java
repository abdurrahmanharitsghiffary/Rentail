package com.goshier.kopos.exception;

import com.goshier.kopos.exception.common.BadRequestException;

public class KosCollaboratorMustUniqueException extends BadRequestException {
    public KosCollaboratorMustUniqueException() {
        super("Kos Collaborator already exists");
    }
}
