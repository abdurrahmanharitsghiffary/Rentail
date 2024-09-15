package com.goshier.kopos.model;

import com.goshier.kopos.enums.CollabolatorRole;
import com.goshier.kopos.model.common.TimestampEntity;
import com.goshier.kopos.model.ids.AgentId;
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
    @MapsId("kosId")
    @ToString.Exclude
    private Accommodation accommodation;

    @NotNull
    @Enumerated
    @Column(nullable = false)
    private CollabolatorRole role;

}
