package com.abdhage.rentail.tenant.model;

import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.common.model.TimestampEntity;
import com.abdhage.rentail.user.model.User;
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
@Table(name = "tenants")
public class Tenant extends TimestampEntity {

    @EmbeddedId
    private TenantId id;

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
