package com.abdhage.rentail.accommodation.impl;

import com.abdhage.rentail.accommodationagent.model.AgentRole;
import com.abdhage.rentail.accommodation.exception.ForbiddenAccommodationActionException;
import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.accommodationagent.AccommodationAgentRepository;
import com.abdhage.rentail.accommodation.AccommodationGuard;
import com.abdhage.rentail.common.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AccommodationGuardImpl implements AccommodationGuard {

    private final AccommodationAgentRepository accommodationAgentRepository;

    public AccommodationGuardImpl(AccommodationAgentRepository accommodationAgentRepository) {
        this.accommodationAgentRepository = accommodationAgentRepository;
    }

    @Override
    public void verifyPermission(AccommodationAgent accommodationAgent) {
        if (accommodationAgent == null || accommodationAgent.getRole() != AgentRole.LANDLORD) {
            throw new ForbiddenAccommodationActionException();
        }
    }

    @Override
    public void verifyPermissionByRoles(AgentId id, List<AgentRole> roles) {
        Optional<AccommodationAgent> agent = accommodationAgentRepository.findById(id);
        agent.ifPresent(accommodationAgent -> {
            if (!roles.contains(accommodationAgent.getRole())) {
                throw new ForbiddenAccommodationActionException();
            }
        });
        agent.orElseThrow(ForbiddenAccommodationActionException::new);
    }

    @Override
    public void verifyPermissionByRoles(AccommodationAgent agent, List<AgentRole> roles) {
        if (agent == null || !roles.contains(agent.getRole())) {
            throw new ForbiddenAccommodationActionException();
        }
    }

    @Override
    public void verifyPermissionById(AgentId id) {
        Optional<AccommodationAgent> accommodationAgent = accommodationAgentRepository.findById(id);

        accommodationAgent.ifPresent(agent -> {
            if (agent.getRole() != AgentRole.LANDLORD) {
                throw new ForbiddenAccommodationActionException();
            }
        });

        accommodationAgent.orElseThrow(ForbiddenAccommodationActionException::new);
    }

    @Override
    public void verifyPermissionBatchByIds(List<AgentId> ids) {
        HashSet<Object> errors = new HashSet<>();

        List<AccommodationAgent> agents = accommodationAgentRepository.findAllById(ids);

        for (AgentId agentId : ids) {
            Optional<AccommodationAgent> searchedAgent = agents
                    .stream()
                    .filter(accommodationAgent -> accommodationAgent.getId() == agentId)
                    .findFirst();

            if (searchedAgent.isEmpty()) {
                errors.add(getForbiddenError(agentId));
            } else {
                if (searchedAgent.get().getRole() != AgentRole.LANDLORD) {
                    errors.add(getForbiddenError(agentId));
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, null, errors);
        }
    }

    @Override
    public void verifyPermissionBatch(List<AccommodationAgent> agents) {
        HashSet<Object> errors = new HashSet<>();

        for (AccommodationAgent agent : agents) {
            if (agent != null && agent.getRole() != AgentRole.LANDLORD) {
                errors.add(getForbiddenError(agent.getId()));
            }
        }

        if (!errors.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, null, errors);
        }
    }

    private Map<String, Object> getForbiddenError(AgentId agentId) {
        Map<String, Object> error = new HashMap<>();

        error.put("message", "Forbidden Resource");
        error.put("agentId", agentId.toString());
        error.put("code", "Forbidden");

        return error;
    }

}