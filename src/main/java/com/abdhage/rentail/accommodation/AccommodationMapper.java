package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.accommodation.dto.CreateAccommodationDTO;
import com.abdhage.rentail.accommodation.dto.UpdateAccommodationDTO;
import com.abdhage.rentail.accommodation.model.Accommodation;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccommodationMapper {
    public AccommodationResponse accommodationToAccommodationResponse(Accommodation accommodation);

    public Accommodation accommodationResponseToAccommodation(AccommodationResponse kos);

    public Accommodation createAccommodationDtoToAccommodation(CreateAccommodationDTO kos);

    public Accommodation updateAccommodationDtoToAccommodation(UpdateAccommodationDTO kos);

}
