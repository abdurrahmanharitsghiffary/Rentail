package com.goshier.kopos.repository;

import com.goshier.kopos.model.AccommodationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AccommodationAddress, UUID> {
}