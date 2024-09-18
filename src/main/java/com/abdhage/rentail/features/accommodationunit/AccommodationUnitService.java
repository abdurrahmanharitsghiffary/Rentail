package com.abdhage.rentail.features.accommodationunit;

import com.abdhage.rentail.features.accommodationunit.dto.CreateUnitDTO;
import com.abdhage.rentail.features.accommodationunit.dto.UpdateUnitDTO;
import com.abdhage.rentail.features.accommodationunit.model.AccommodationUnit;
import com.abdhage.rentail.common.service.BatchCrudService;

import java.util.List;
import java.util.UUID;

public interface AccommodationUnitService extends BatchCrudService<AccommodationUnitResponse, CreateUnitDTO, UpdateUnitDTO, UUID> {
    public AccommodationUnit checkAccommodationUnitMustExists(UUID id);

    public List<AccommodationUnitResponse> findAllByAccommodationId(UUID accommodationId);
}
