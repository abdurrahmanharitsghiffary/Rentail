package com.goshier.kopos.model;

import com.goshier.kopos.enums.MembershipPlanType;
import com.goshier.kopos.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership_plans")
public class MembershipPlan extends BaseEntity {
    @Column(length = 100)
    @NotEmpty
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    @PositiveOrZero
    @NotNull
    private Long price;

    @Enumerated
    @Column(nullable = false)
    @NotNull
    private MembershipPlanType type;

    @OneToMany(mappedBy = "membershipPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Membership> memberships;
}
