package com.goshier.kopos.repository;

import com.goshier.kopos.model.AccommodationAgent;
import com.goshier.kopos.model.ids.AgentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KosCollaboratorRepository extends JpaRepository<AccommodationAgent, AgentId> {
    List<AccommodationAgent> findById_KosId(@NonNull UUID kosId);
}

