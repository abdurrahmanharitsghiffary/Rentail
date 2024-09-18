package com.abdhage.rentail.features.accommodation.impl;

import com.abdhage.rentail.features.accommodationagent.model.AgentRole;
import com.abdhage.rentail.features.accommodation.exception.ForbiddenAccommodationActionException;
import com.abdhage.rentail.features.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.features.accommodationagent.model.AgentId;
import com.abdhage.rentail.features.accommodationagent.AccommodationAgentRepository;
import com.abdhage.rentail.features.accommodation.AccommodationGuard;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccommodationGuardImpl implements AccommodationGuard {

    private final AccommodationAgentRepository accommodationAgentRepository;

    public AccommodationGuardImpl(AccommodationAgentRepository accommodationAgentRepository) {
        this.accommodationAgentRepository = accommodationAgentRepository;
    }

    @Override
    @Deprecated
    public void verifyPermission(AccommodationAgent accommodationAgent) {
        if (accommodationAgent == null) throw new ForbiddenAccommodationActionException();
        if (accommodationAgent.getRole() != AgentRole.LANDLORD) throw new ForbiddenAccommodationActionException();
    }

    @Override
    public void verifyPermissionById(AgentId id) {
        Optional<AccommodationAgent> accommodationAgent = accommodationAgentRepository.findById(id);

        accommodationAgent.ifPresent(kosCollaborator -> {
            if (kosCollaborator.getRole() != AgentRole.LANDLORD) throw new ForbiddenAccommodationActionException();
        });

        accommodationAgent.orElseThrow(ForbiddenAccommodationActionException::new);
    }
}
