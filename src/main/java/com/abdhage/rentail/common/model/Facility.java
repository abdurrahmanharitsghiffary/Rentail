package com.abdhage.rentail.common.model;

import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_id")
    @ToString.Exclude
    private Accommodation accommodation;

    @JsonInclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_unit_id")
    @ToString.Exclude
    private AccommodationUnit accommodationUnit;
}
