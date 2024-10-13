package com.abdhage.rentail.tenant.impl;

import com.abdhage.rentail.accommodation.AccommodationChecker;
import com.abdhage.rentail.accommodation.AccommodationGuard;
import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.accommodationagent.model.AgentId;
import com.abdhage.rentail.accommodationunit.AccommodationUnitService;
import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.auth.AuthService;
import com.abdhage.rentail.common.exception.NotFoundException;
import com.abdhage.rentail.tenant.TenantMapper;
import com.abdhage.rentail.tenant.TenantRepository;
import com.abdhage.rentail.tenant.TenantResponse;
import com.abdhage.rentail.tenant.TenantService;
import com.abdhage.rentail.tenant.dto.CreateTenantDTO;
import com.abdhage.rentail.tenant.dto.UpdateTenantDTO;
import com.abdhage.rentail.tenant.dto.UpdateTenantManyDTO;
import com.abdhage.rentail.tenant.model.Tenant;
import com.abdhage.rentail.tenant.model.TenantId;
import com.abdhage.rentail.user.UserChecker;
import com.abdhage.rentail.user.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final AccommodationGuard accommodationGuard;
    private final AuthService authService;
    private final AccommodationChecker accommodationChecker;
    private final TenantMapper tenantMapper;
    private final UserChecker userChecker;
    private final AccommodationUnitService accommodationUnitService;

    public TenantServiceImpl(TenantRepository tenantRepository, AccommodationGuard accommodationGuard, AuthService authService, AccommodationChecker accommodationChecker,
                             TenantMapper tenantMapper, UserChecker userChecker, AccommodationUnitService accommodationUnitService) {
        this.tenantRepository = tenantRepository;
        this.accommodationGuard = accommodationGuard;
        this.authService = authService;
        this.accommodationChecker = accommodationChecker;
        this.tenantMapper = tenantMapper;
        this.userChecker = userChecker;
        this.accommodationUnitService = accommodationUnitService;
    }

    @Override
    public List<TenantResponse> findAll() {
        return tenantRepository.findAll()
                .stream()
                .map(tenantMapper::tenantToTenantResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TenantResponse create(CreateTenantDTO dto) {
        User loggedUser = authService.getLoggedUserInformations();

        AccommodationUnit unit = accommodationUnitService
                .checkAccommodationUnitMustExists(dto.getUnitId());

        // TODO : should use another method to improve performance
        accommodationGuard
                .verifyPermissionById(new AgentId(unit.getAccommodation().getId(), loggedUser.getId()));

        User tenantUser = userChecker.checkUserMustExists(dto.getUserId());

        Tenant tenant = new Tenant();

        tenant.setUnit(unit);
        tenant.setUser(tenantUser);
        tenant.setEndAt(dto.getEndAt());

        return tenantMapper
                .tenantToTenantResponse(tenantRepository.save(tenant));
    }

    @Override
    public TenantResponse update(TenantId tenantId, UpdateTenantDTO dto) {
        Tenant tenant = verifyPermission(tenantId);

        if (dto.getEndAt() != null) {
            tenant.setEndAt(dto.getEndAt());
        }

        return tenantMapper
                .tenantToTenantResponse(tenantRepository.save(tenant));
    }

    @Override
    public void destroy(TenantId tenantId) {
        Tenant tenant = verifyPermission(tenantId);
        tenantRepository.delete(tenant);
    }

    @Override
    public TenantResponse findOne(TenantId tenantId) {
        return tenantMapper
                .tenantToTenantResponse(checkTenantMustExists(tenantId));
    }

    @Override
    public Tenant checkTenantMustExists(TenantId id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tenant not found"));
    }

    // TODO : Should authorize each data before updating
    @Override
    public int updateMany(UpdateTenantManyDTO dto) {

        int updatedCount = 0;
        Map<Date, List<UpdateTenantManyDTO.UpdateDTO>> dtoGroupedByEndAt = dto
                .getData()
                .stream()
                .collect(Collectors.groupingBy(UpdateTenantManyDTO.UpdateDTO::getEndAt));

        for (Map.Entry<Date, List<UpdateTenantManyDTO.UpdateDTO>> entry : dtoGroupedByEndAt.entrySet()) {

            Date endAt = entry.getKey();
            List<UpdateTenantManyDTO.UpdateDTO> updateDTOS = entry.getValue();

            List<TenantId> tenantIds = new ArrayList<>(List.of());

            updateDTOS.forEach(updateDTO -> tenantIds
                    .add(new TenantId(updateDTO.getUnitId(), updateDTO.getUserId())));

            final int count = tenantRepository.updateManyEndAt(endAt, tenantIds);
            updatedCount += count;

        }

        return updatedCount;
    }

    // TODO : Authorize all ids first
    @Override
    public void destroyMany(List<TenantId> ids) {
        tenantRepository.deleteAllByIdInBatch(ids);
    }

    @Override
    public List<TenantResponse> findAllByUnit(UUID id) {
        Accommodation accommodation = accommodationChecker.checkAccommodationMustExists(id);
        return tenantRepository
                .findByUnit_Id(accommodation.getId())
                .stream()
                .map(tenantMapper::tenantToTenantResponse)
                .collect(Collectors.toList());
    }

    private Tenant verifyPermission(TenantId tenantId) {
        Tenant tenant = checkTenantMustExists(tenantId);
        User loggedUser = authService.getLoggedUserInformations();
        // TODO : Should use another method to improve the performance
        accommodationGuard
                .verifyPermissionById(new AgentId(tenant.getUnit().getAccommodation().getId(), loggedUser.getId()));

        return tenant;
    }

}
