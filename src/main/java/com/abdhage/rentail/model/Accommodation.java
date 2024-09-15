package com.abdhage.rentail.model;

import com.abdhage.rentail.model.common.BaseEntity;
import com.abdhage.rentail.model.enums.AccommodationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "accommodation")
public class Accommodation extends BaseEntity {

    @Column(length = 150, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "accommodation", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "accommodation", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Facility> facilities = new HashSet<>();

    @Column(nullable = false, name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "accommodation_type", nullable = false)
    private AccommodationType accommodationType;

    @Lob
    private String description;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<AccommodationAgent> collaborators;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<AccommodationUnit> rooms;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Message> messages;

    @ManyToMany(mappedBy = "bookmarks", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    private Set<User> bookers;

    @OneToOne(mappedBy = "accommodation", optional = false)
    @ToString.Exclude
    private AccommodationAddress address;
}
