package com.abdhage.rentail.accommodationagent.impl;

import com.abdhage.rentail.accommodationagent.dto.CreateAgentDTO;
import com.abdhage.rentail.accommodationagent.dto.UpdateAgentDTO;
import com.abdhage.rentail.accommodationagent.AccommodationAgentMapper;
import com.abdhage.rentail.user.UserMapper;
import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.user.model.User;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.accommodationagent.AccommodationAgentRepository;
import com.abdhage.rentail.accommodationagent.AccommodationAgentResponse;
import com.abdhage.rentail.accommodationagent.AccommodationAgentService;
import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.accommodationagent.AccommodationAgentChecker;
import com.abdhage.rentail.user.UserChecker;
import com.abdhage.rentail.accommodation.AccommodationGuard;
import com.abdhage.rentail.accommodation.AccommodationChecker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccommodationAgentServiceImpl implements AccommodationAgentService {

    private final AuthService authService;
    private final UserChecker userChecker;
    private final UserMapper userMapper;
    private final AccommodationGuard accommodationGuard;
    private final AccommodationChecker accommodationChecker;
    private final AccommodationAgentRepository accommodationAgentRepository;
    private final AccommodationAgentMapper accommodationAgentMapper;
    private final AccommodationAgentChecker accommodationAgentChecker;

    public AccommodationAgentServiceImpl(AuthService authService, UserChecker userChecker, UserMapper userMapper, AccommodationGuard accommodationGuard, AccommodationChecker accommodationChecker, AccommodationAgentRepository accommodationAgentRepository,
                                         AccommodationAgentMapper accommodationAgentMapper, AccommodationAgentChecker accommodationAgentChecker) {
        this.authService = authService;
        this.userChecker = userChecker;
        this.userMapper = userMapper;
        this.accommodationGuard = accommodationGuard;
        this.accommodationChecker = accommodationChecker;
        this.accommodationAgentRepository = accommodationAgentRepository;
        this.accommodationAgentMapper = accommodationAgentMapper;
        this.accommodationAgentChecker = accommodationAgentChecker;
    }

    @Override
    public List<AccommodationAgentResponse> findAll() {
        return this.accommodationAgentRepository.findAll().stream().map(accommodationAgentMapper::agentToAgentResponse).collect(Collectors.toList());
    }

    @Override
    public List<AccommodationAgentResponse> findAllByAccommodationId(UUID id) {
        accommodationChecker.checkAccommodationMustExists(id);
        return this.accommodationAgentRepository.findById_KosId(id)
                .stream().map(accommodationAgentMapper::agentToAgentResponse).collect(Collectors.toList());
    }

    @Override
    public AccommodationAgentResponse findOne(AgentId id) {
        return accommodationAgentMapper.agentToAgentResponse(accommodationAgentChecker.checkAgentMustExists(id));
    }

    @Override
    @Transactional
    public AccommodationAgentResponse update(AgentId id, UpdateAgentDTO dto) {
        final User loggedUser = authService.getLoggedUserInformations();
        accommodationGuard.verifyPermissionById(new AgentId(id.getAccommodationId(), loggedUser.getId()));
        final AccommodationAgent theCollaborator = accommodationAgentChecker.checkAgentMustExists(id);

        if (dto.getRole() != null) theCollaborator.setRole(dto.getRole());

        final AccommodationAgent updatedAccommodationAgent = accommodationAgentRepository.save(theCollaborator);

        return accommodationAgentMapper.agentToAgentResponse(updatedAccommodationAgent);
    }

    @Override
    @Transactional
    public AccommodationAgentResponse create(CreateAgentDTO dto) {
        final User loggedUser = authService.getLoggedUserInformations();

        accommodationGuard.verifyPermissionById(new AgentId(dto.getAccommodationId(), loggedUser.getId()));
        userChecker.checkUserMustExists(dto.getUserId());
        accommodationAgentChecker.checkAgentMustUnique(new AgentId(dto.getAccommodationId(), dto.getUserId()));

        final AccommodationAgent accommodationAgent = accommodationAgentMapper.createAgentDtoToAgent(dto);

        final AccommodationAgent createdAccommodationAgent = accommodationAgentRepository.save(accommodationAgent);
        return accommodationAgentMapper.agentToAgentResponse(createdAccommodationAgent);
    }

    @Override
    @Transactional
    public void destroy(AgentId id) {
        final User loggedUser = authService.getLoggedUserInformations();

        accommodationGuard.verifyPermissionById(new AgentId(id.getAccommodationId(), loggedUser.getId()));
        accommodationAgentChecker.checkAgentMustExists(id);

        accommodationAgentRepository.deleteById(id);
    }

    @Override
    public int destroyMany(List<UUID>
                                   ids, UUID id) {
        final User loggedUser = authService.getLoggedUserInformations();
        accommodationGuard.verifyPermissionById(new AgentId(id, loggedUser.getId()));


        final List<User> users = userChecker.checkBatchUserMustExists(ids);
        final List<UUID> userIds = userMapper.usersToUserIds(users);
        final List<AgentId> collaboratorIds = userIdsToCollaboratorIds(userIds, id);

        accommodationAgentRepository.deleteAllByIdInBatch(collaboratorIds);

        return userIds.size();
    }

    @Override
    public int updateMany(List<UUID> ids, UUID id, UpdateAgentDTO dto) {
        final User loggedUser = authService.getLoggedUserInformations();
        accommodationGuard.verifyPermissionById(new AgentId(id, loggedUser.getId()));

        final List<User> users = userChecker.checkBatchUserMustExists(ids);
        final List<UUID> userIds = userMapper.usersToUserIds(users);

        final List<AgentId> agentIds = userIdsToCollaboratorIds(userIds, id);

        final List<AccommodationAgent> collaborators = accommodationAgentRepository.findAllById(agentIds).stream().map(agent -> {
            agent.setRole(dto.getRole());
            return agent;
        }).toList();

        accommodationAgentRepository.saveAll(collaborators);

        return userIds.size();
    }

    private List<AgentId> userIdsToCollaboratorIds(List<UUID> ids, UUID accommodationId) {
        return ids.stream().map(id -> new AgentId(accommodationId, id)).toList();
    }

}
