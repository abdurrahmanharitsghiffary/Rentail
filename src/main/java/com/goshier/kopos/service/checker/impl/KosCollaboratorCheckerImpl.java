package com.goshier.kopos.service.checker.impl;

import com.goshier.kopos.exception.KosCollaboratorNotFoundException;
import com.goshier.kopos.exception.KosCollaboratorMustUniqueException;
import com.goshier.kopos.model.AccommodationAgent;
import com.goshier.kopos.model.ids.AgentId;
import com.goshier.kopos.repository.KosCollaboratorRepository;
import com.goshier.kopos.service.checker.KosCollaboratorChecker;
import com.goshier.kopos.service.checker.KosChecker;
import com.goshier.kopos.service.checker.UserChecker;
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
