package com.goshier.kopos.repository;

import com.goshier.kopos.model.Membership;
import com.goshier.kopos.model.ids.MembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipId> {
}