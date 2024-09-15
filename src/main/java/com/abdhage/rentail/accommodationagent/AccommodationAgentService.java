package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.common.service.BaseCrudService;
import com.abdhage.rentail.common.service.BatchCrudService;

import java.util.List;
import java.util.UUID;

public interface
AccommodationAgentService extends BaseCrudService<AccommodationAgentResponse, CreateAgentDTO, UpdateAgentDTO, AgentId> {
    public List<AccommodationAgentResponse> findAllByAccommodationId(UUID id);

    public int updateMany(List<UUID> ids, UUID id, UpdateAgentDTO dto);

    public int destroyMany(List<UUID>
                                   ids, UUID id);
}
