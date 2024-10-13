package com.abdhage.rentail.accommodation.repository;

import com.abdhage.rentail.accommodation.model.Accommodation;
import com.abdhage.rentail.accommodation.model.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, UUID> {

    public Optional<Accommodation> findByName(String name);

    public List<Accommodation> findByAccommodationType(AccommodationType accommodationType);

}
