package com.abdhage.rentail.accommodationunit.model;

import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.booking.model.Booking;
import com.abdhage.rentail.common.model.*;
import com.abdhage.rentail.tenant.model.Tenant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodation_units")

public class AccommodationUnit extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @OneToMany(mappedBy = "accommodationUnit", fetch = FetchType.EAGER)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "accommodationUnit", fetch = FetchType.EAGER)
    private Set<Facility> facilities = new HashSet<>();

    @Column(name = "booking_price", nullable = false)
    private Long bookingPrice = 0L;

    @Column(name = "unit_type", nullable = false)
    private UnitType unitType;

    @Column(nullable = false)
    private Long price;

    @Lob
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", nullable = false)
    @ToString.Exclude
    private Accommodation accommodation;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Booking> accommodationBookings;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Tenant> occupiers;
}
