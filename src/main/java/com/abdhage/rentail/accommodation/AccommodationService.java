package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.common.model.OrderByOption;
import com.abdhage.rentail.accommodation.dto.CreateAccommodationDTO;
import com.abdhage.rentail.accommodation.dto.UpdateAccommodationDTO;
import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.accommodation.model.AccommodationType;
import com.abdhage.rentail.common.service.BaseCrudService;

import java.util.List;
import java.util.UUID;

public interface AccommodationService extends BaseCrudService<AccommodationResponse, CreateAccommodationDTO, UpdateAccommodationDTO, UUID> {
    public List<AccommodationResponse> findAllByType(AccommodationType type);

    public List<AccommodationResponse> search(String q, String column, OrderByOption orderBy);
}
