package com.abdhage.rentail.features.accommodationunit.dto;

import com.abdhage.rentail.features.accommodationunit.model.UnitType;
import com.abdhage.rentail.common.model.Facility;
import com.abdhage.rentail.common.model.Image;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUnitDTO {
    @Size(min = 2)
    private String name;

    private Boolean isActive = false;

    private Set<Image> images = new HashSet<>();

    private Set<Facility> facilities = new HashSet<>();

    @PositiveOrZero
    private Long bookingPrice = 0L;

    private UnitType unitType;

    @PositiveOrZero
    private Long price;

    @Size(min = 2)
    private String description;

}
