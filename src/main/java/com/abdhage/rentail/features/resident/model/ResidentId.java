package com.abdhage.rentail.features.resident.model;

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
public class ResidentId {
    @Column(name = "unitId", nullable = false)
    private UUID unitId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
