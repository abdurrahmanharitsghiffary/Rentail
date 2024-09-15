package com.abdhage.rentail.repository;

import com.abdhage.rentail.model.Membership;
import com.abdhage.rentail.model.ids.MembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipId> {
}