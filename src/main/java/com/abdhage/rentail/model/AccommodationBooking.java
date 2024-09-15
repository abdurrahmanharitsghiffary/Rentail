package com.abdhage.rentail.model;

import com.abdhage.rentail.model.enums.BookingStatus;
import com.abdhage.rentail.model.common.TimestampEntity;
import com.abdhage.rentail.model.ids.BookingId;
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
public class AccommodationBooking extends TimestampEntity {

    @EmbeddedId
    private BookingId id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @ToString.Exclude
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @MapsId("unitId")
    @ToString.Exclude
    private AccommodationUnit unit;

    @Lob
    private String notes;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.PENDING;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private Date expirationDate;
}
