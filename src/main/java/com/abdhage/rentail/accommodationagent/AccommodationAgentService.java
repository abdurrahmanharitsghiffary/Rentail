package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentManyDTO;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.common.service.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface
AccommodationAgentService extends BaseCrudService<AccommodationAgentResponse, CreateAgentDTO, UpdateAgentDTO, AgentId> {
    public List<AccommodationAgentResponse> findAllByAccommodationId(UUID id);

    public int updateMany(UUID id, List<UpdateAgentManyDTO> dto);

    public int destroyMany(UUID id, List<UUID> ids);
}
