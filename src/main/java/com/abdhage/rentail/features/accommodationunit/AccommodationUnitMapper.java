package com.abdhage.rentail.features.accommodationunit;

import com.abdhage.rentail.features.accommodationunit.dto.CreateUnitDTO;
import com.abdhage.rentail.features.accommodationunit.dto.UpdateUnitDTO;
import com.abdhage.rentail.features.accommodationunit.model.AccommodationUnit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccommodationUnitMapper {

    public AccommodationUnit accommodationUnitResponseToAccommodationUnit(AccommodationUnitResponse accommodationUnitResponse);

    public AccommodationUnitResponse accommodationUnitToAccommodationUnitResponse(AccommodationUnit accommodationUnit);

    public AccommodationUnit createAccommodationUnitDtoToAccommodationUnit(CreateUnitDTO dto);

    public AccommodationUnit updateAccommodationUnitDtoToAccommodationUnit(UpdateUnitDTO dto);
}
