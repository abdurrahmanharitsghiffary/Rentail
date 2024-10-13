package com.abdhage.rentail.accommodationagent.dto;

import com.abdhage.rentail.accommodationagent.model.AgentRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAgentManyDTO {
    private UUID id;
    private AgentRole role;
}
