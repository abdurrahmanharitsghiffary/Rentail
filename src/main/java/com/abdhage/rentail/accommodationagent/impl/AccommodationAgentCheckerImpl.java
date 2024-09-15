package com.abdhage.rentail.accommodationagent.impl;

import com.abdhage.rentail.accommodationagent.exception.AccommodationAgentNotFoundException;
import com.abdhage.rentail.accommodationagent.exception.AccommodationAgentMustUniqueException;
import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.accommodationagent.AccommodationAgentRepository;
import com.abdhage.rentail.user.UserChecker;
import com.abdhage.rentail.accommodationagent.AccommodationAgentChecker;
import com.abdhage.rentail.accommodation.AccommodationChecker;
import org.springframework.stereotype.Component;

@Component
public class AccommodationAgentCheckerImpl implements AccommodationAgentChecker {

    private final AccommodationChecker accommodationChecker;
    private final UserChecker userChecker;
    private final AccommodationAgentRepository accommodationAgentRepository;

    public AccommodationAgentCheckerImpl(AccommodationChecker accommodationChecker, UserChecker userChecker, AccommodationAgentRepository accommodationAgentRepository) {
        this.accommodationChecker = accommodationChecker;
        this.userChecker = userChecker;
        this.accommodationAgentRepository = accommodationAgentRepository;
    }

    @Override
    public void checkAgentMustUnique(AgentId id) {
        accommodationAgentRepository.findById(id).ifPresent(kosCollaborator -> {
            throw new AccommodationAgentMustUniqueException();
        });
    }

    @Override
    public AccommodationAgent checkAgentMustExists(AgentId id) {
        accommodationChecker.checkAccommodationMustExists(id.getAccommodationId());
        userChecker.checkUserMustExists(id.getUserId());
        return accommodationAgentRepository.findById(id).orElseThrow(AccommodationAgentNotFoundException::new);
    }

    @Override
    public AccommodationAgent checkUserAssociated(AgentId id) {
        return accommodationAgentRepository.findById(id).orElseThrow();
    }
}
