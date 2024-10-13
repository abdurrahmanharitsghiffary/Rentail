package com.abdhage.rentail.tenant.model;

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
public class TenantId {
    @Column(name = "unit_id", nullable = false)
    private UUID unitId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
