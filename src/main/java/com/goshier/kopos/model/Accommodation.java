package com.goshier.kopos.model;

import com.goshier.kopos.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "accommodation", fetch = FetchType.EAGER)
    private Set<Image> images = new HashSet<>();

    @Column(columnDefinition = "text[]")
    private List<String> facilities = new ArrayList<>();

    public List<String> getFacilities() {
        if (facilities == null) return new ArrayList<>();
        return facilities;
    }

    @Column(nullable = false, name = "is_verified")
    private Boolean isVerified = false;

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

    @OneToOne(mappedBy = "accommodation", optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private AccommodationAddress accommodationAddress;
}
