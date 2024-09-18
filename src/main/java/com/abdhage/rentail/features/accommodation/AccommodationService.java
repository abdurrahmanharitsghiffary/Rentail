package com.abdhage.rentail.features.accommodation;

import com.abdhage.rentail.features.accommodation.dto.CreateAccommodationDTO;
import com.abdhage.rentail.features.accommodation.dto.UpdateAccommodationDTO;
import com.abdhage.rentail.features.accommodation.model.AccommodationType;
import com.abdhage.rentail.common.service.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface AccommodationService extends BaseCrudService<AccommodationResponse, CreateAccommodationDTO, UpdateAccommodationDTO, UUID> {
    public List<AccommodationResponse> findAllByType(AccommodationType type);
}
