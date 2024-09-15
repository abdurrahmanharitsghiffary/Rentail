package com.abdhage.rentail.model;

import com.abdhage.rentail.model.common.BaseEntity;
import com.abdhage.rentail.model.enums.UnitType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", nullable = false)
    @ToString.Exclude
    private Accommodation accommodation;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<AccommodationBooking> accommodationBookings;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Resident> occupiers;
}
