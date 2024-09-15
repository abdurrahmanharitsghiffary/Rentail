package com.abdhage.rentail.service.guard;

import com.abdhage.rentail.model.AccommodationAgent;
import com.abdhage.rentail.model.ids.AgentId;

public interface KosGuard {
    public void verifyPermissionById(AgentId id);

    public void verifyPermission(AccommodationAgent accommodationAgent);

}
