package com.abdhage.rentail.dtos.kos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKosDto {
    private String name;

    private List<String> features;

    private String description;
}
