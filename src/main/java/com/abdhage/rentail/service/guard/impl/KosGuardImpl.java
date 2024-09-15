package com.abdhage.rentail.service.guard.impl;

import com.abdhage.rentail.model.enums.AgentRole;
import com.abdhage.rentail.exception.ForbiddenKosActionException;
import com.abdhage.rentail.model.AccommodationAgent;
import com.abdhage.rentail.model.ids.AgentId;
import com.abdhage.rentail.repository.KosCollaboratorRepository;
import com.abdhage.rentail.service.guard.KosGuard;
import org.springframework.stereotype.Component;

@Component
public class KosGuardImpl implements KosGuard {

    private final KosCollaboratorRepository kosCollaboratorRepository;

    public KosGuardImpl(KosCollaboratorRepository kosCollaboratorRepository) {
        this.kosCollaboratorRepository = kosCollaboratorRepository;
    }

    @Override
    public void verifyPermission(AccommodationAgent accommodationAgent) {
        if (accommodationAgent.getRole() != AgentRole.LANDLORD) throw new ForbiddenKosActionException();
    }

    @Override
    public void verifyPermissionById(AgentId id) {
        kosCollaboratorRepository.findById(id).ifPresent(kosCollaborator -> {
            if (kosCollaborator.getRole() != AgentRole.LANDLORD) throw new ForbiddenKosActionException();
        });
    }
}
