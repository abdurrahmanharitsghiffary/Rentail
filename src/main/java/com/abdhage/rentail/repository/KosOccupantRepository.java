package com.abdhage.rentail.repository;

import com.abdhage.rentail.model.Resident;
import com.abdhage.rentail.model.ids.ResidentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KosOccupantRepository extends JpaRepository<Resident, ResidentId> {
}