package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.accommodationagent.model.AgentId;

public interface AccommodationGuard {
    public void verifyPermissionById(AgentId id);

    public void verifyPermission(AccommodationAgent accommodationAgent);

}
