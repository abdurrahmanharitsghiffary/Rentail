package com.abdhage.rentail.model.ids;

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
    @Column(name = "kos_id", nullable = false)
    private UUID kosId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
