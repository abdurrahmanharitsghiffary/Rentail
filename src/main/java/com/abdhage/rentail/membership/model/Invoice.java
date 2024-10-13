package com.abdhage.rentail.membership.model;

import com.abdhage.rentail.common.model.BaseEntity;
import com.abdhage.rentail.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
public class Invoice extends BaseEntity {
    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false, unique = true, name = "invoice_id")
    private String invoiceId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private InvoiceStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<InvoiceItem> items;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<InvoiceCharge> charges;
}
