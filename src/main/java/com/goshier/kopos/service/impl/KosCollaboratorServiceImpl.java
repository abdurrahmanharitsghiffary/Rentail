package com.goshier.kopos.service.impl;

import com.goshier.kopos.dtos.kos.collaborator.CreateKosCollaboratorDto;
import com.goshier.kopos.dtos.kos.collaborator.UpdateKosCollaboratorDto;
import com.goshier.kopos.mapper.KosCollaboratorMapper;
import com.goshier.kopos.mapper.UserMapper;
import com.goshier.kopos.model.AccommodationAgent;
import com.goshier.kopos.model.User;
import com.goshier.kopos.model.ids.AgentId;
import com.goshier.kopos.repository.KosCollaboratorRepository;
import com.goshier.kopos.response.KosCollaboratorResponse;
import com.goshier.kopos.service.KosCollaboratorService;
import com.goshier.kopos.service.checker.KosCollaboratorChecker;
import com.goshier.kopos.service.checker.UserChecker;
import com.goshier.kopos.service.guard.KosGuard;
import com.goshier.kopos.service.checker.KosChecker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KosCollaboratorServiceImpl implements KosCollaboratorService {

    private final UserChecker userChecker;
    private final UserMapper userMapper;
    private final KosGuard kosGuard;
    private final KosChecker kosChecker;
    private final KosCollaboratorRepository kosCollaboratorRepository;
    private final KosCollaboratorMapper kosCollaboratorMapper;
    private final KosCollaboratorChecker kosCollaboratorValidator;

    public KosCollaboratorServiceImpl(UserChecker userChecker, UserMapper userMapper, KosGuard kosGuard, KosChecker kosChecker, KosCollaboratorRepository kosCollaboratorRepository,
                                      KosCollaboratorMapper kosCollaboratorMapper, KosCollaboratorChecker kosCollaboratorValidator) {
        this.userChecker = userChecker;
        this.userMapper = userMapper;
        this.kosGuard = kosGuard;
        this.kosChecker = kosChecker;
        this.kosCollaboratorRepository = kosCollaboratorRepository;
        this.kosCollaboratorMapper = kosCollaboratorMapper;
        this.kosCollaboratorValidator = kosCollaboratorValidator;
    }

    public List<KosCollaboratorResponse> findAll() {
        return this.kosCollaboratorRepository.findAll().stream().map(kosCollaboratorMapper::kosCollaboratorToKosCollaboratorResponse).collect(Collectors.toList());
    }

    public List<KosCollaboratorResponse> findAllByKosId(UUID kosId) {
        kosChecker.checkKosMustExists(kosId);
        return this.kosCollaboratorRepository.findById_KosId(kosId)
                .stream().map(kosCollaboratorMapper::kosCollaboratorToKosCollaboratorResponse).collect(Collectors.toList());
    }

    public KosCollaboratorResponse findOne(AgentId id) {
        return kosCollaboratorMapper.kosCollaboratorToKosCollaboratorResponse(kosCollaboratorValidator.checkCollaboratorMustExists(id));
    }

    @Transactional
    public KosCollaboratorResponse update(AgentId id, UUID userId, UpdateKosCollaboratorDto dto) {
        final AccommodationAgent theCollaborator = kosCollaboratorValidator.checkCollaboratorMustExists(id);
        kosGuard.verifyPermission(theCollaborator);

        if (dto.getRole() != null) theCollaborator.setRole(dto.getRole());

        final AccommodationAgent updatedAccommodationAgent = kosCollaboratorRepository.save(theCollaborator);

        return kosCollaboratorMapper.kosCollaboratorToKosCollaboratorResponse(updatedAccommodationAgent);
    }

    @Transactional
    public KosCollaboratorResponse create(CreateKosCollaboratorDto dto, UUID userId) {
        kosChecker.checkKosMustExists(dto.getKosId());
        userChecker.checkUserMustExists(userId);
        kosCollaboratorValidator.checkKosCollaboratorMustUnique(new AgentId(dto.getKosId(), dto.getUserId()));

        final AccommodationAgent accommodationAgent = kosCollaboratorMapper.createKosCollaboratorDtoToKosCollaborator(dto);

        final AccommodationAgent createdAccommodationAgent = kosCollaboratorRepository.save(accommodationAgent);
        return kosCollaboratorMapper.kosCollaboratorToKosCollaboratorResponse(createdAccommodationAgent);
    }

    @Transactional
    public void destroy(AgentId id, UUID userId) {
        final AccommodationAgent accommodationAgent = kosCollaboratorValidator.checkCollaboratorMustExists(id);
        kosGuard.verifyPermission(accommodationAgent);
        kosCollaboratorRepository.deleteById(id);
    }

    public Integer destroyMany(List<UUID>
                                       ids, UUID kosId, UUID userId) {
        kosGuard.verifyPermissionById(new AgentId(kosId, userId));

        final List<User> users = userChecker.checkBatchUserMustExists(ids);
        final List<UUID> userIds = userMapper.usersToUserIds(users);
        final List<AgentId> collaboratorIds = userIdsToCollaboratorIds(userIds, kosId);

        kosCollaboratorRepository.deleteAllByIdInBatch(collaboratorIds);

        return userIds.size();
    }

    public Integer updateMany(List<UUID> ids, UUID kosId, UUID userId, UpdateKosCollaboratorDto dto) {
        kosGuard.verifyPermissionById(new AgentId(kosId, userId));

        final List<User> users = userChecker.checkBatchUserMustExists(ids);
        final List<UUID> userIds = userMapper.usersToUserIds(users);

        final List<AgentId> collaboratorIds = userIdsToCollaboratorIds(userIds, kosId);

        final List<AccommodationAgent> collaborators = kosCollaboratorRepository.findAllById(collaboratorIds).stream().map(kosCollaborator -> {
            kosCollaborator.setRole(dto.getRole());
            return kosCollaborator;
        }).toList();

        kosCollaboratorRepository.saveAll(collaborators);

        return userIds.size();
    }

    private List<AgentId> userIdsToCollaboratorIds(List<UUID> ids, UUID kosId) {
        return ids.stream().map(id -> new AgentId(kosId, id)).toList();
    }

}
