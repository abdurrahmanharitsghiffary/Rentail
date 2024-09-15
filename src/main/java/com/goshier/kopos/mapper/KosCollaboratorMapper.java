package com.goshier.kopos.mapper;

import com.goshier.kopos.dtos.kos.collaborator.CreateKosCollaboratorDto;
import com.goshier.kopos.dtos.kos.collaborator.UpdateKosCollaboratorDto;
import com.goshier.kopos.model.AccommodationAgent;
import com.goshier.kopos.response.KosCollaboratorResponse;
import org.mapstruct.Mapper;

@Mapper
public interface KosCollaboratorMapper {
    public KosCollaboratorResponse kosCollaboratorToKosCollaboratorResponse(AccommodationAgent accommodationAgent);

    public AccommodationAgent kosCollaboratorResponseToKosCollaborator(KosCollaboratorResponse kosCollaborator);

    public AccommodationAgent createKosCollaboratorDtoToKosCollaborator(CreateKosCollaboratorDto createKosCollaboratorDto);

    public AccommodationAgent updateKosCollaboratorDtoToKosCollaborator(UpdateKosCollaboratorDto createKosCollaboratorDto);
}
