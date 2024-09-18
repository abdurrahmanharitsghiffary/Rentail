package com.abdhage.rentail.features.resident.model;

import com.abdhage.rentail.features.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.common.model.TimestampEntity;
import com.abdhage.rentail.features.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "residents")
public class Resident extends TimestampEntity {

    @EmbeddedId
    private ResidentId id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("unitId")
    @ToString.Exclude
    private AccommodationUnit unit;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("userId")
    @ToString.Exclude
    private User user;

    @Column(name = "end_at", nullable = false)
    private Date endAt;
}
