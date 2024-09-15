package com.goshier.kopos.repository;

import com.goshier.kopos.model.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KosRoomRepository extends JpaRepository<AccommodationUnit, UUID> {
}