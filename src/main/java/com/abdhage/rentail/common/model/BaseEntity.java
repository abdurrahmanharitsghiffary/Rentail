package com.abdhage.rentail.common.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity extends TimestampEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;
}
