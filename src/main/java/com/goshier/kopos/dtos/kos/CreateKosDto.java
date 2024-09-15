package com.goshier.kopos.dtos.kos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateKosDto {
    private String name;

    private List<String> features;

    private String description;

}
