package com.abdhage.rentail.accommodationunit.dto;

import com.abdhage.rentail.accommodationunit.model.UnitType;
import com.abdhage.rentail.common.model.Facility;
import com.abdhage.rentail.common.model.Image;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUnitDTO {
    @NotEmpty
    @Size(min = 2)
    private String name;

    @NotNull
    private Boolean isActive = false;

    private Set<Image> images = new HashSet<>();

    private Set<Facility> facilities = new HashSet<>();

    @PositiveOrZero
    private Long bookingPrice = 0L;

    @NotNull
    private UnitType unitType;

    @NotNull
    @PositiveOrZero
    private Long price;

    @Size(min = 2)
    private String description;

    @NotNull
    private UUID accommodationId;
}
