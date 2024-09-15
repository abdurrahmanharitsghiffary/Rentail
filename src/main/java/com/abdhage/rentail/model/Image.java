package com.abdhage.rentail.model;

import com.abdhage.rentail.model.common.BaseEntityLong;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Accommodation accommodation;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_unit")
    @ToString.Exclude
    private AccommodationUnit accommodationUnit;
}
