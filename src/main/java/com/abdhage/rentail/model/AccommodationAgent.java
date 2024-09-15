package com.abdhage.rentail.model;

import com.abdhage.rentail.model.enums.AgentRole;
import com.abdhage.rentail.model.common.TimestampEntity;
import com.abdhage.rentail.model.ids.AgentId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private AgentId id = new AgentId();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @MapsId("accommodationId")
    @ToString.Exclude
    private Accommodation accommodation;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private AgentRole role;

}
