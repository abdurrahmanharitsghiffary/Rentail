package com.abdhage.rentail.accommodationagent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AgentId {
    @Column(name = "accommodation_id", nullable = false)
    private UUID accommodationId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
