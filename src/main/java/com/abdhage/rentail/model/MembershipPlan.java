package com.abdhage.rentail.model;

import com.abdhage.rentail.model.enums.MembershipPlanType;
import com.abdhage.rentail.model.common.BaseEntity;
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
    @Column(length = 100, nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private Long price;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private MembershipPlanType type;

    @OneToMany(mappedBy = "membershipPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Membership> memberships;
}
