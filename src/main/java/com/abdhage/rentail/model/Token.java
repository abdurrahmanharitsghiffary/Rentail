package com.abdhage.rentail.model;

import com.abdhage.rentail.enums.TokenType;
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

    @NotNull
    @Column(nullable = false)
    @Enumerated
    private TokenType type;

    @Column(name = "expires_at")
    private Long expiresAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false, name = "user_id")
    @ToString.Exclude
    private User user;

}
