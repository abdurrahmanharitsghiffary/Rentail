package com.goshier.kopos.model;

import com.goshier.kopos.enums.UserRole;
import com.goshier.kopos.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @NotNull
    @Email
    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Pattern(message = "Password must contain at least 1 Uppercase, 1 Lowercase, and 1 Number", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    @NotEmpty
    @Column(nullable = false)
    private String password;

    @NotNull
    @Enumerated
    @Column(nullable = false)
    private UserRole role;

    @Size(min = 2)
    @NotEmpty
    @Column(nullable = false, name = "display_name")
    private String displayName;

    @Size(min = 2)
    @NotEmpty
    @Column(nullable = false, unique = true, length = 150)
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<UserAddress> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Token> tokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Account> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<AccommodationAgent> ownedKos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Membership> memberships;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<AccommodationBooking> accommodationBookings;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Message> authoredMessages;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Message> receivedMessages;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "bookmarks", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "kos_id")})
    @ToString.Exclude
    private Set<Accommodation> bookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Resident> occupiedKos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public @Pattern(message = "Password must contain at least 1 Uppercase, 1 Lowercase, and 1 Number", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$") @NotEmpty String getPassword() {
        return password;
    }

    public String getRealUsername() {
        return username;
    }
}
