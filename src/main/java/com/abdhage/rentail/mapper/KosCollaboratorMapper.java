package com.abdhage.rentail.mapper;

import com.abdhage.rentail.dtos.kos.collaborator.CreateKosCollaboratorDto;
import com.abdhage.rentail.dtos.kos.collaborator.UpdateKosCollaboratorDto;
import com.abdhage.rentail.model.AccommodationAgent;
import com.abdhage.rentail.response.KosCollaboratorResponse;
import org.mapstruct.Mapper;

@Mapper
public interface KosCollaboratorMapper {
    public KosCollaboratorResponse kosCollaboratorToKosCollaboratorResponse(AccommodationAgent accommodationAgent);

    public AccommodationAgent kosCollaboratorResponseToKosCollaborator(KosCollaboratorResponse kosCollaborator);

    public AccommodationAgent createKosCollaboratorDtoToKosCollaborator(CreateKosCollaboratorDto createKosCollaboratorDto);

    public AccommodationAgent updateKosCollaboratorDtoToKosCollaborator(UpdateKosCollaboratorDto createKosCollaboratorDto);
}
