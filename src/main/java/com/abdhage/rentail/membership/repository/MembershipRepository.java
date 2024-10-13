package com.abdhage.rentail.membership.repository;

import com.abdhage.rentail.membership.model.Membership;
import com.abdhage.rentail.membership.model.MembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipId> {
}