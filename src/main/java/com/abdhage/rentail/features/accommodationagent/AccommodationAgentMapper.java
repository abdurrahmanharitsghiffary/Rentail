package com.abdhage.rentail.features.accommodationagent;

import com.abdhage.rentail.features.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.features.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.features.accommodationagent.model.AccommodationAgent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccommodationAgentMapper {
    public AccommodationAgentResponse agentToAgentResponse(AccommodationAgent accommodationAgent);

    public AccommodationAgent agentResponseToAgent(AccommodationAgentResponse kosCollaborator);

    public AccommodationAgent createAgentDtoToAgent(CreateAgentDTO createAgentDTO);

    public AccommodationAgent updateKosCollaboratorDtoToKosCollaborator(UpdateAgentDTO createKosCollaboratorDto);
}
