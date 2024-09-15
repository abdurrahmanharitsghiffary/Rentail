package com.goshier.kopos.model;

import com.goshier.kopos.model.common.BaseEntity;
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

    @Email
    private String email;

    @Lob
    private String description;

    @Lob
    private String address;

    @NotEmpty
    private String regency;

    @NotEmpty
    private String province;

    @NotEmpty
    private String district;

    @Column(name = "urban_village")
    private String urbanVillage;

    @Column(name = "postal_code")
    private String postalCode;

    private String longitude;

    private String latitude;

    @Column(name = "is_main_address")
    private String isMainAddress;


}
