package com.abdhage.rentail.token.model;

import com.abdhage.rentail.common.model.BaseEntity;
import com.abdhage.rentail.user.model.User;
import jakarta.persistence.*;
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

    @Column(name = "expires_at")
    private Long expiresAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "user_id")
    @ToString.Exclude
    private User user;

}
