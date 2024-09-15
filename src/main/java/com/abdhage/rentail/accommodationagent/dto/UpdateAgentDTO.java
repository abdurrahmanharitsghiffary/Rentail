package com.abdhage.rentail.accommodationagent.dto;

import com.abdhage.rentail.accommodationagent.model.AgentRole;
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
