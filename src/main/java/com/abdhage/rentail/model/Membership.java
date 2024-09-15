package com.abdhage.rentail.model;

import com.abdhage.rentail.model.common.TimestampEntity;
import com.abdhage.rentail.model.ids.MembershipId;
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

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("membershipPlanId")
    private MembershipPlan membershipPlan;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_at")
    @CreationTimestamp
    private Date endAt;

}
