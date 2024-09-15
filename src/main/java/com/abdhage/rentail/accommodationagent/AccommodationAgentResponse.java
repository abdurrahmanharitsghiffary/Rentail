package com.abdhage.rentail.accommodationagent;

import com.abdhage.rentail.accommodationagent.model.AgentId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccommodationAgentResponse {
    private AgentId id;
}
