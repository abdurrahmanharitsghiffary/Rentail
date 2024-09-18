package com.abdhage.rentail.common.model;

import com.abdhage.rentail.features.accommodation.model.Accommodation;
import com.abdhage.rentail.features.accommodationunit.model.AccommodationUnit;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntityLong {

    @Lob
    private String description;

    @Column(nullable = false)
    private String src;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_unit")
    @ToString.Exclude
    private AccommodationUnit accommodationUnit;
}
