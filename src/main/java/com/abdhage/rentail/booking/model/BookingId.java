package com.abdhage.rentail.booking.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingId {
    @JoinColumn(name = "user_id", nullable = false)
    private UUID userId;

    @JoinColumn(name = "unit_id", nullable = false)
    private UUID unitId;
}
