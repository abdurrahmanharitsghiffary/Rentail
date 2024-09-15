package com.goshier.kopos.service.checker;

import com.goshier.kopos.model.AccommodationAgent;
import com.goshier.kopos.model.ids.AgentId;

public interface KosCollaboratorChecker {
    public void checkKosCollaboratorMustUnique(AgentId id);

    public AccommodationAgent checkCollaboratorMustExists(AgentId id);
}
