package com.abdhage.rentail.tenant;

import com.abdhage.rentail.tenant.dto.CreateTenantDTO;
import com.abdhage.rentail.tenant.dto.UpdateTenantDTO;
import com.abdhage.rentail.tenant.model.TenantId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("units/{id}/tenants")
    public List<TenantResponse> findAll(@PathVariable UUID id) {
        return tenantService.findAllByUnit(id);
    }

    @GetMapping("units/{unitId}/tenants/{tenantId}")
    public TenantResponse findOne(@PathVariable("unitId") UUID unitId, @PathVariable("tenantId") UUID tenantId) {
        return tenantService.findOne(new TenantId(unitId, tenantId));
    }

    @PostMapping("tenants")
    public TenantResponse create(@Valid @RequestBody CreateTenantDTO createTenantDTO) {
        return tenantService.create(createTenantDTO);
    }

    @PatchMapping("units/{unitId}/tenants/{tenantId}")
    public TenantResponse update(@PathVariable("unitId") UUID unitId, @PathVariable("tenantId") UUID tenantId, @Valid @RequestBody UpdateTenantDTO updateTenantDTO) {
        return tenantService.update(new TenantId(unitId, tenantId), updateTenantDTO);
    }

    @DeleteMapping("units/{unitId}/tenants/{tenantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("unitId") UUID unitId, @PathVariable("tenantId") UUID tenantId) {
        tenantService.destroy(new TenantId(unitId, tenantId));
    }

}
