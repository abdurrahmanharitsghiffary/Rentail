package com.goshier.kopos.service.guard;

import com.goshier.kopos.model.AccommodationAgent;
import com.goshier.kopos.model.ids.AgentId;

public interface KosGuard {
    public void verifyPermissionById(AgentId id);

    public void verifyPermission(AccommodationAgent accommodationAgent);

}
