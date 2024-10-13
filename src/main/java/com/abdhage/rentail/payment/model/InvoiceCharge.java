package com.abdhage.rentail.payment.model;

import com.abdhage.rentail.common.model.BaseEntity;
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
@Table(name = "invoice_charges")
public class InvoiceCharge extends BaseEntity {
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ChargeType type;

    @Column(nullable = false)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    @ToString.Exclude
    private Invoice invoice;
}
