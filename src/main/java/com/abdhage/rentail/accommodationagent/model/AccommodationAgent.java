package com.abdhage.rentail.accommodationagent.model;

import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.user.model.User;
import com.abdhage.rentail.common.model.TimestampEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "accommodation_agents")
public class AccommodationAgent extends TimestampEntity {

    @EmbeddedId
    private AgentId id;

    @ManyToOne(optional = false)
    @MapsId("userId")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("accommodationId")
    @ToString.Exclude
    private Accommodation accommodation;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private AgentRole role;

}
