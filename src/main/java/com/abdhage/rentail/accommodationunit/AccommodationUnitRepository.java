package com.abdhage.rentail.accommodationunit;

import com.abdhage.rentail.accommodationunit.model.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, UUID> {
    public List<AccommodationUnit> findByAccommodation_Id(UUID id);
}