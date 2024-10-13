package com.abdhage.rentail.tenant;

import com.abdhage.rentail.common.service.BaseCrudService;
import com.abdhage.rentail.tenant.dto.CreateTenantDTO;
import com.abdhage.rentail.tenant.dto.UpdateTenantDTO;
import com.abdhage.rentail.tenant.dto.UpdateTenantManyDTO;
import com.abdhage.rentail.tenant.model.Tenant;
import com.abdhage.rentail.tenant.model.TenantId;

import java.util.List;
import java.util.UUID;

public interface TenantService extends BaseCrudService<TenantResponse, CreateTenantDTO, UpdateTenantDTO, TenantId> {
    public Tenant checkTenantMustExists(TenantId id);

    public int updateMany(UpdateTenantManyDTO dto);

    public void destroyMany(List<TenantId> ids);

    public List<TenantResponse> findAllByUnit(UUID id);
}
