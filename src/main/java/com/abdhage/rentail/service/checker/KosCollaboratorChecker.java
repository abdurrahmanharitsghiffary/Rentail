package com.abdhage.rentail.service.checker;

import com.abdhage.rentail.model.AccommodationAgent;
import com.abdhage.rentail.model.ids.AgentId;

public interface KosCollaboratorChecker {
    public void checkKosCollaboratorMustUnique(AgentId id);

    public AccommodationAgent checkCollaboratorMustExists(AgentId id);
}
