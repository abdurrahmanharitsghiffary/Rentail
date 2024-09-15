package com.goshier.kopos.model.ids;

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

    @JoinColumn(name = "kos_room_id", nullable = false)
    private UUID roomId;
}
