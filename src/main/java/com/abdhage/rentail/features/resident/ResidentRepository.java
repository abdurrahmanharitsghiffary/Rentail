package com.abdhage.rentail.features.resident;

import com.abdhage.rentail.features.resident.model.Resident;
import com.abdhage.rentail.features.resident.model.ResidentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, ResidentId> {
}