package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccommodationAgentMapper {
    public AccommodationAgentResponse agentToAgentResponse(AccommodationAgent accommodationAgent);

    public AccommodationAgent agentResponseToAgent(AccommodationAgentResponse kosCollaborator);

    public AccommodationAgent createAgentDtoToAgent(CreateAgentDTO createAgentDTO);

    public AccommodationAgent updateKosCollaboratorDtoToKosCollaborator(UpdateAgentDTO createKosCollaboratorDto);
}
