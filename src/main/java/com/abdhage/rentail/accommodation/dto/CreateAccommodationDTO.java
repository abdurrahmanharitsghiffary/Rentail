package com.abdhage.rentail.accommodation.dto;

import com.abdhage.rentail.common.validation.EnumValidator;
import com.abdhage.rentail.accommodation.model.AccommodationType;
import com.abdhage.rentail.common.model.Facility;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccommodationDTO {
    @NotNull
    private String name;

    private List<Facility> facilities;

    private String description;

    @NotNull
    @EnumValidator(enumClazz = AccommodationType.class)
    private String accommodationType;

}
