package com.abdhage.rentail.features.membership.model;

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
public class MembershipId {
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "membership_plan_id", nullable = false)
    private UUID membershipPlanId;
}
