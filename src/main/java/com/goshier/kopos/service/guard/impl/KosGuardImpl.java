package com.goshier.kopos.service.guard.impl;

import com.goshier.kopos.enums.CollabolatorRole;
import com.goshier.kopos.exception.ForbiddenKosActionException;
import com.goshier.kopos.model.AccommodationAgent;
import com.goshier.kopos.model.ids.AgentId;
import com.goshier.kopos.repository.KosCollaboratorRepository;
import com.goshier.kopos.service.guard.KosGuard;
import org.springframework.stereotype.Component;

@Component
public class KosGuardImpl implements KosGuard {

    private final KosCollaboratorRepository kosCollaboratorRepository;

    public KosGuardImpl(KosCollaboratorRepository kosCollaboratorRepository) {
        this.kosCollaboratorRepository = kosCollaboratorRepository;
    }

    @Override
    public void verifyPermission(AccommodationAgent accommodationAgent) {
        if (accommodationAgent.getRole() != CollabolatorRole.CREATOR) throw new ForbiddenKosActionException();
    }

    @Override
    public void verifyPermissionById(AgentId id) {
        kosCollaboratorRepository.findById(id).ifPresent(kosCollaborator -> {
            if (kosCollaborator.getRole() != CollabolatorRole.CREATOR) throw new ForbiddenKosActionException();
        });
    }
}
