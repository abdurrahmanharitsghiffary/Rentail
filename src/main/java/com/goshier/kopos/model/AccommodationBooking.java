package com.goshier.kopos.model;

import com.goshier.kopos.enums.BookingStatus;
import com.goshier.kopos.model.common.TimestampEntity;
import com.goshier.kopos.model.ids.BookingId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @MapsId("roomId")
    @ToString.Exclude
    private AccommodationUnit room;

    @Lob
    private String notes;

    @NotNull
    @Enumerated
    @Column(nullable = false)
    private BookingStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private Date expirationDate;
}
