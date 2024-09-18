package com.abdhage.rentail.features.accommodationagent.dto;

import com.abdhage.rentail.features.accommodationagent.model.AgentRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgentDTO {
    @NotNull
    private AgentRole role;

    @NotNull
    private UUID accommodationId;

    @NotNull
    private UUID userId;
}
