package com.abdhage.rentail.features.user.model;

import com.abdhage.rentail.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user_addresses")
public class UserAddress extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

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

    @Column(name = "postal_code")
    private String postalCode;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String latitude;

    @Column(name = "is_main_address")
    private Boolean isMainAddress = false;


}
