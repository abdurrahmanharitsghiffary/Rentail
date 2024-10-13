package com.abdhage.rentail.tenant.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTenantDTO {
    @NotNull
    private UUID userId;

    @NotNull
    private UUID unitId;

    @Future
    private Date endAt;
}
