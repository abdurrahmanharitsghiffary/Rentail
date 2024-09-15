package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.model.AccommodationAgent;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccommodationAgentRepository extends JpaRepository<AccommodationAgent, AgentId> {
    List<AccommodationAgent> findById_KosId(@NonNull UUID kosId);
}

