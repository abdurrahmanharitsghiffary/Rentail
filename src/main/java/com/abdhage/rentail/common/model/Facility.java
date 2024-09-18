package com.abdhage.rentail.common.model;

import com.abdhage.rentail.features.accommodation.model.Accommodation;
import com.abdhage.rentail.features.accommodationunit.model.AccommodationUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "facilities")
public class Facility extends BaseEntityLong {
    @Lob
    private String description;

    private String image;

    @JsonInclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    @ToString.Exclude
    private Accommodation accommodation;

    @JsonInclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_unit_id")
    @ToString.Exclude
    private AccommodationUnit accommodationUnit;
}
