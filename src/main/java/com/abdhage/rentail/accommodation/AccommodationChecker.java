package com.abdhage.rentail.accommodation;

import com.abdhage.rentail.accommodation.model.Accommodation;

import java.util.UUID;

public interface AccommodationChecker {
    public Accommodation checkAccommodationMustExists(UUID id);

    public void checkAccommodationNameMustUnique(String name);

    public void checkFacilitiesMustBeBelowTwenty(UUID id);

    public void checkFacilitiesMustBeBelowTwenty(Accommodation accommodation);

    public void checkImagesMustBeBelowTwenty(UUID id);

    public void checkImagesMustBeBelowTwenty(Accommodation accommodation);

}
