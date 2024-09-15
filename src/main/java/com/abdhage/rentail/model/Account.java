package com.abdhage.rentail.model;

import com.abdhage.rentail.model.enums.ProviderType;
import com.abdhage.rentail.model.common.BaseEntity;
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
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"provider_id", "provider_type"})
})
public class Account extends BaseEntity {

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "provider_type", nullable = false)
    private ProviderType providerType;

    private String password;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
}
