package com.abdhage.rentail.dtos.kos.collaborator;

import com.abdhage.rentail.enums.CollabolatorRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateKosCollaboratorDto {
    @NotNull
    private CollabolatorRole role;

    private UUID kosId;

    private UUID userId;
}
