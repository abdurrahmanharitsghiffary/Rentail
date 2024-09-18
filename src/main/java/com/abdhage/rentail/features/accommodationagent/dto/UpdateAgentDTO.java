package com.abdhage.rentail.features.accommodationagent.dto;

import com.abdhage.rentail.features.accommodationagent.model.AgentRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAgentDTO {
    @NotNull
    private AgentRole role;
}
