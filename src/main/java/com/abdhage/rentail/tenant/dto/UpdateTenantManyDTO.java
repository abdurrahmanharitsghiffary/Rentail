package com.abdhage.rentail.tenant.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTenantManyDTO {
    @Valid
    private List<UpdateDTO> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateDTO {
        @Future
        private Date endAt;

        private UUID userId;
        private UUID unitId;
    }
}
