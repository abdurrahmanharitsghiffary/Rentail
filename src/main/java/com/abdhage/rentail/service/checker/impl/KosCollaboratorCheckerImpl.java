package com.abdhage.rentail.service.checker.impl;

import com.abdhage.rentail.exception.KosCollaboratorNotFoundException;
import com.abdhage.rentail.exception.KosCollaboratorMustUniqueException;
import com.abdhage.rentail.model.AccommodationAgent;
import com.abdhage.rentail.model.ids.AgentId;
import com.abdhage.rentail.repository.KosCollaboratorRepository;
import com.abdhage.rentail.service.checker.KosCollaboratorChecker;
import com.abdhage.rentail.service.checker.KosChecker;
import com.abdhage.rentail.service.checker.UserChecker;
import org.springframework.stereotype.Component;

@Component
public class KosCollaboratorCheckerImpl implements KosCollaboratorChecker {

    private final KosChecker kosChecker;
    private final UserChecker userChecker;
    private final KosCollaboratorRepository kosCollaboratorRepository;

    public KosCollaboratorCheckerImpl(KosChecker kosChecker, UserChecker userChecker, KosCollaboratorRepository kosCollaboratorRepository) {
        this.kosChecker = kosChecker;
        this.userChecker = userChecker;
        this.kosCollaboratorRepository = kosCollaboratorRepository;
    }

    public void checkKosCollaboratorMustUnique(AgentId id) {
        kosCollaboratorRepository.findById(id).ifPresent(kosCollaborator -> {
            throw new KosCollaboratorMustUniqueException();
        });
    }

    public AccommodationAgent checkCollaboratorMustExists(AgentId id) {
        kosChecker.checkKosMustExists(id.getKosId());
        userChecker.checkUserMustExists(id.getUserId());
        return kosCollaboratorRepository.findById(id).orElseThrow(KosCollaboratorNotFoundException::new);
    }
}
