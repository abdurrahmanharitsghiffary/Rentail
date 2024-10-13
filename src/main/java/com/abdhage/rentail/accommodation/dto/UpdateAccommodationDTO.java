package com.abdhage.rentail.accommodation.dto;

import com.abdhage.rentail.common.validation.EnumValidator;
import com.abdhage.rentail.accommodation.model.AccommodationType;
import com.abdhage.rentail.common.model.Facility;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccommodationDTO {
    private String name;

    @Valid
    private List<Facility> facilities;

    private String description;

    @EnumValidator(enumClazz = AccommodationType.class)
    private String accommodationType;
}
