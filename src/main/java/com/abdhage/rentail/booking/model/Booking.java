package com.abdhage.rentail.booking.model;

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
@Table(name = "accommodation_bookings")
public class Booking extends TimestampEntity {

    @EmbeddedId
    private BookingId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("unitId")
    @ToString.Exclude
    private AccommodationUnit unit;

    @Lob
    private String notes;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.PENDING;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startFrom")
    private Date startFrom;
}
