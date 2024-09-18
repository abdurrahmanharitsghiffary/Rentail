package com.abdhage.rentail.features.accommodation;

import com.abdhage.rentail.features.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.features.accommodationagent.model.AgentId;

public interface AccommodationGuard {
    public void verifyPermissionById(AgentId id);

    public void verifyPermission(AccommodationAgent accommodationAgent);

}
