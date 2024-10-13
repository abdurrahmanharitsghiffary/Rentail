package com.abdhage.rentail.accommodation.impl;

import com.abdhage.rentail.accommodation.exception.AccommodationNotFoundException;
import com.abdhage.rentail.accommodation.exception.AccommodationNameMustUniqueException;
import com.abdhage.rentail.common.exception.BadRequestException;
import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.accommodation.repository.AccommodationRepository;
import com.abdhage.rentail.accommodation.AccommodationChecker;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccommodationCheckerImpl implements AccommodationChecker {

    private final AccommodationRepository accommodationRepository;

    public AccommodationCheckerImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public void checkAccommodationNameMustUnique(String name) {
        accommodationRepository.findByName(name).ifPresent((accommodation) -> {
            throw new AccommodationNameMustUniqueException();
        });
    }

    @Override
    public void checkFacilitiesMustBeBelowTwenty(UUID id) {

    }

    @Override
    public void checkFacilitiesMustBeBelowTwenty(Accommodation accommodation) {
        int facilitiesCount = accommodation.getFacilities().size();
        if (facilitiesCount > 20) throw new BadRequestException("Facilities must be lower or equals than 20");
    }

    @Override
    public void checkImagesMustBeBelowTwenty(UUID id) {

    }

    @Override
    public void checkImagesMustBeBelowTwenty(Accommodation accommodation) {

    }

    @Override
    public Accommodation checkAccommodationMustExists(UUID id) {
        return accommodationRepository.findById(id).orElseThrow(AccommodationNotFoundException::new);
    }
}
