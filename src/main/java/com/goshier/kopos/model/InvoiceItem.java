package com.goshier.kopos.model;

import com.goshier.kopos.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotNull
    private String name;

    @Lob
    private String description;

    @PositiveOrZero
    @Column(nullable = false)
    @NotNull
    private Long price;

    @Column(nullable = false)
    @Positive
    @NotNull
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false, name = "invoice_id")
    @ToString.Exclude
    private Invoice invoice;
}
