package com.abdhage.rentail.features.accommodationagent;

import com.abdhage.rentail.features.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.features.accommodationagent.model.AgentId;

public interface AccommodationAgentChecker {
    public void checkAgentMustUnique(AgentId id);

    public AccommodationAgent checkAgentMustExists(AgentId id);

    public AccommodationAgent checkUserAssociated(AgentId id);
}
