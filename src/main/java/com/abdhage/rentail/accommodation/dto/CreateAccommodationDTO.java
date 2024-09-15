package com.abdhage.rentail.accommodation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccommodationDTO {
    private String name;

    private List<String> features;

    private String description;

}
