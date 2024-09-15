package com.abdhage.rentail.model;

import com.abdhage.rentail.model.enums.TokenType;
import com.abdhage.rentail.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "tokens")
public class Token extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TokenType type;

    @Column(name = "expires_at", nullable = false)
    private Long expiresAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false, name = "user_id")
    @ToString.Exclude
    private User user;

}
