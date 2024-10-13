package com.abdhage.rentail.membership.repository;

import com.abdhage.rentail.payment.model.InvoiceCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceChargeRepository extends JpaRepository<InvoiceCharge, UUID> {
}