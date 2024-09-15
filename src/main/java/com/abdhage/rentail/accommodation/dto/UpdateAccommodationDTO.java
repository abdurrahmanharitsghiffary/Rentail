package com.abdhage.rentail.accommodation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccommodationDTO {
    private String name;

    private List<String> features;

    private String description;
}
