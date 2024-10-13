package com.abdhage.rentail.tenant;

import com.abdhage.rentail.tenant.model.Tenant;
import com.abdhage.rentail.tenant.model.TenantId;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, TenantId> {
    List<Tenant> findByUnit_Id(UUID id);

    @Modifying
    @Query("UPDATE Tenant t SET t.endAt = :endAt WHERE (t.userId, t.unitId) IN (:tenantIdPairs)")
    int updateManyEndAt(@Param("endAt") Date endAt, @Param("tenantIdPairs") List<TenantId> tenantIdPairs);
}