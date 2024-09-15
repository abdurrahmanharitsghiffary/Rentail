package com.goshier.kopos.service;

import com.goshier.kopos.dtos.kos.collaborator.CreateKosCollaboratorDto;
import com.goshier.kopos.dtos.kos.collaborator.UpdateKosCollaboratorDto;
import com.goshier.kopos.model.ids.AgentId;
import com.goshier.kopos.response.KosCollaboratorResponse;
import com.goshier.kopos.service.common.AuthorizedCrudService;

import java.util.List;
import java.util.UUID;

public interface
KosCollaboratorService extends AuthorizedCrudService<KosCollaboratorResponse, CreateKosCollaboratorDto, UpdateKosCollaboratorDto, AgentId, UUID> {

    public Integer updateMany(List<UUID> ids, UUID kosId, UUID userId, UpdateKosCollaboratorDto dto);

    public Integer destroyMany(List<UUID> ids, UUID kosId, UUID userId);
}
