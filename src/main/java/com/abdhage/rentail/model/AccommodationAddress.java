package com.abdhage.rentail.model;

import com.abdhage.rentail.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "accommodation_addresses")
public class AccommodationAddress extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    @ToString.Exclude
    private Accommodation accommodation;

    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Lob
    private String description;

    @Lob
    private String address;

    @Column(nullable = false)
    private String regency;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String district;

    @Column(name = "urban_village")
    private String urbanVillage;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String latitude;

}
