package com.abdhage.rentail.model;

import com.abdhage.rentail.model.enums.ChargeType;
import com.abdhage.rentail.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    @ToString.Exclude
    private Invoice invoice;
}
