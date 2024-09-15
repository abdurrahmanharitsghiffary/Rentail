package com.abdhage.rentail.accommodation.impl;

import com.abdhage.rentail.accommodation.AccommodationChecker;
import com.abdhage.rentail.accommodationagent.model.AgentRole;
import com.abdhage.rentail.accommodation.exception.ForbiddenAccommodationActionException;
import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.accommodationagent.AccommodationAgentRepository;
import com.abdhage.rentail.accommodation.AccommodationGuard;
import com.abdhage.rentail.common.exception.ForbiddenException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccommodationGuardImpl implements AccommodationGuard {

    private final AccommodationChecker accommodationChecker;
    private final AccommodationAgentRepository accommodationAgentRepository;

    public AccommodationGuardImpl(AccommodationChecker accommodationChecker, AccommodationAgentRepository accommodationAgentRepository) {
        this.accommodationChecker = accommodationChecker;
        this.accommodationAgentRepository = accommodationAgentRepository;
    }

    @Override
    public void verifyPermission(AccommodationAgent accommodationAgent) {
        if (accommodationAgent == null) throw new ForbiddenAccommodationActionException();
        if (accommodationAgent.getRole() != AgentRole.LANDLORD) throw new ForbiddenAccommodationActionException();
    }

    @Override
    public void verifyPermissionById(AgentId id) {
        accommodationChecker.checkAccommodationMustExists(id.getAccommodationId());
        Optional<AccommodationAgent> accommodationAgent = accommodationAgentRepository.findById(id);

        accommodationAgent.ifPresent(kosCollaborator -> {
            if (kosCollaborator.getRole() != AgentRole.LANDLORD) throw new ForbiddenAccommodationActionException();
        });

        accommodationAgent.orElseThrow(ForbiddenAccommodationActionException::new);
    }
}
