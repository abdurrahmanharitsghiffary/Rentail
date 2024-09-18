package com.abdhage.rentail.features.accommodation.repository;

import com.abdhage.rentail.features.accommodation.model.AccommodationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccommodationAddressRepository extends JpaRepository<AccommodationAddress, UUID> {
}