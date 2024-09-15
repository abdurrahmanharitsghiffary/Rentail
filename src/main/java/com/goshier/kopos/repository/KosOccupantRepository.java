package com.goshier.kopos.repository;

import com.goshier.kopos.model.Resident;
import com.goshier.kopos.model.ids.ResidentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KosOccupantRepository extends JpaRepository<Resident, ResidentId> {
}