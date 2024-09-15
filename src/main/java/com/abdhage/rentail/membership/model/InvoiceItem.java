package com.abdhage.rentail.membership.model;

import com.abdhage.rentail.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_items")
public class InvoiceItem extends BaseEntity {
    @Column(name = "item_id")
    private String itemId;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false, name = "invoice_id")
    @ToString.Exclude
    private Invoice invoice;
}
