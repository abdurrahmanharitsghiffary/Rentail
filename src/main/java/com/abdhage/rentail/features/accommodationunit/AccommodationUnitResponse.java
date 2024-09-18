package com.abdhage.rentail.features.accommodationunit;

import com.abdhage.rentail.features.accommodationunit.model.UnitType;
import com.abdhage.rentail.common.model.Facility;
import com.abdhage.rentail.common.model.Image;
import com.abdhage.rentail.common.response.BaseResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccommodationUnitResponse extends BaseResponse<UUID> {
    private String name;
    private Boolean isActive = false;
    private Set<Image> images = new HashSet<>();
    private Set<Facility> facilities = new HashSet<>();
    private Long bookingPrice = 0L;
    private UnitType unitType;
    private Long price;
    private String description;
}
