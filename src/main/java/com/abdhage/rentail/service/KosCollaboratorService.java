package com.abdhage.rentail.service;

import com.abdhage.rentail.dtos.kos.collaborator.CreateKosCollaboratorDto;
import com.abdhage.rentail.dtos.kos.collaborator.UpdateKosCollaboratorDto;
import com.abdhage.rentail.model.ids.AgentId;
import com.abdhage.rentail.response.KosCollaboratorResponse;
import com.abdhage.rentail.service.common.AuthorizedCrudService;

import java.util.List;
import java.util.UUID;

public interface
KosCollaboratorService extends AuthorizedCrudService<KosCollaboratorResponse, CreateKosCollaboratorDto, UpdateKosCollaboratorDto, AgentId, UUID> {

    public Integer updateMany(List<UUID> ids, UUID kosId, UUID userId, UpdateKosCollaboratorDto dto);

    public Integer destroyMany(List<UUID> ids, UUID kosId, UUID userId);
}
