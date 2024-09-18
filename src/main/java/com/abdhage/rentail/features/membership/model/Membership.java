package com.abdhage.rentail.features.membership.model;

import com.abdhage.rentail.common.model.TimestampEntity;
import com.abdhage.rentail.features.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "memberships")
public class Membership extends TimestampEntity {

    @EmbeddedId
    private MembershipId id;

    @ManyToOne(optional = false)
    @MapsId("userId")
    private User user;

    @ManyToOne(optional = false)
    @MapsId("membershipPlanId")
    private MembershipPlan membershipPlan;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_at")
    @CreationTimestamp
    private Date endAt;

}
