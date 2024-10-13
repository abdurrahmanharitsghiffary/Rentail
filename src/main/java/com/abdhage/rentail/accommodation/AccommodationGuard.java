package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.accommodation.model.AccommodationType;
import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.accommodationagent.model.AgentRole;

import java.util.List;

public interface AccommodationGuard {
    public void verifyPermissionById(AgentId id);

    public void verifyPermission(AccommodationAgent accommodationAgent);

    public void verifyPermissionByRoles(AgentId id, List<AgentRole> roles);

    public void verifyPermissionByRoles(AccommodationAgent agent, List<AgentRole> roles);

    public void verifyPermissionBatchByIds(List<AgentId> ids);

    public void verifyPermissionBatch(List<AccommodationAgent> agents);

}
