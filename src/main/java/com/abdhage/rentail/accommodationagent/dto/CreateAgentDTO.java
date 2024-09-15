package com.abdhage.rentail.accommodationagent.dto;

import com.abdhage.rentail.accommodationagent.model.AgentRole;
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

    private UUID accommodationId;

    private UUID userId;
}
