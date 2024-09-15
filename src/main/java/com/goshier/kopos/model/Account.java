package com.goshier.kopos.model;

import com.goshier.kopos.enums.ProviderType;
import com.goshier.kopos.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(message = "Password must contain at least 1 Uppercase, 1 Lowercase, and 1 Number", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
}
