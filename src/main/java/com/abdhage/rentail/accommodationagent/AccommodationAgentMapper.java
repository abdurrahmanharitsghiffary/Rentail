package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentManyDTO;
import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccommodationAgentMapper {
    public AccommodationAgentResponse agentToAgentResponse(AccommodationAgent accommodationAgent);

    public AccommodationAgent agentResponseToAgent(AccommodationAgentResponse accommodationAgent);

    public AccommodationAgent createAgentDtoToAgent(CreateAgentDTO createAgentDTO);

    public AccommodationAgent updateAccommodationAgentDtoToAccommodationAgent(UpdateAgentDTO createAccommodationAgentDto);

    public UUID updateManyAgentDtoToId(UpdateAgentManyDTO dto);

    public List<UUID> updateManyAgentDtoToIds(List<UpdateAgentManyDTO> dto);

}
