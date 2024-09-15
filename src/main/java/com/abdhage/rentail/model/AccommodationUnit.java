package com.abdhage.rentail.model;

import com.abdhage.rentail.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @NotEmpty
    private String name;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive = false;

    @OneToMany(mappedBy = "accommodationUnit", fetch = FetchType.EAGER)
    private Set<Image> images = new HashSet<>();

    @Column(columnDefinition = "text[]")
    private List<String> facilities;

    public List<String> getFacilities() {
        if (facilities == null) return new ArrayList<>();
        return facilities;
    }

    @NotNull
    @PositiveOrZero
    @Column(name = "booking_price", nullable = false)
    private Long bookingPrice = 0L;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Long price;

    @Lob
    private String description;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "kos_id", nullable = false)
    @ToString.Exclude
    private Accommodation accommodation;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<AccommodationBooking> accommodationBookings;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Resident> occupiers;
}
