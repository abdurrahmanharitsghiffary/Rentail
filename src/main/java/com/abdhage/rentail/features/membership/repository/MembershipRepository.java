package com.abdhage.rentail.features.membership.repository;

import com.abdhage.rentail.features.membership.model.Membership;
import com.abdhage.rentail.features.membership.model.MembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipId> {
}