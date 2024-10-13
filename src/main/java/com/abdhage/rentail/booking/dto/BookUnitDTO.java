package com.abdhage.rentail.booking.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookUnitDTO {

    private String notes;
    @NotNull
    private UUID unitId;

}
