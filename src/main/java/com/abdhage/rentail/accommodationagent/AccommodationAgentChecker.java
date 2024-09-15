package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.accommodationagent.model.AgentId;

public interface AccommodationAgentChecker {
    public void checkAgentMustUnique(AgentId id);

    public AccommodationAgent checkAgentMustExists(AgentId id);

    public AccommodationAgent checkUserAssociated(AgentId id);
}
