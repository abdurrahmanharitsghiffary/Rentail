package com.abdhage.rentail.accommodationunit;

import com.abdhage.rentail.accommodationunit.dto.CreateUnitDTO;
import com.abdhage.rentail.accommodationunit.dto.UpdateUnitDTO;
import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccommodationUnitMapper {

    public AccommodationUnit accommodationUnitResponseToAccommodationUnit(AccommodationUnitResponse accommodationUnitResponse);

    public AccommodationUnitResponse accommodationUnitToAccommodationUnitResponse(AccommodationUnit accommodationUnit);

    public AccommodationUnit createAccommodationUnitDtoToAccommodationUnit(CreateUnitDTO dto);

    public AccommodationUnit updateAccommodationUnitDtoToAccommodationUnit(UpdateUnitDTO dto);
}
