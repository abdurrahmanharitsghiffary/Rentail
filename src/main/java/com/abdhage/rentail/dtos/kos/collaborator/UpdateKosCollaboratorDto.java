package com.abdhage.rentail.dtos.kos.collaborator;

import com.abdhage.rentail.enums.CollabolatorRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateKosCollaboratorDto {
    @NotNull
    private CollabolatorRole role;
}
