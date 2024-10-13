package com.abdhage.rentail.tenant;

import com.abdhage.rentail.tenant.model.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    public TenantResponse tenantToTenantResponse(Tenant tenant);

    public Tenant tenantResponseToTenant(TenantResponse tenantResponse);
}
