package com.abdhage.rentail.repository;

import com.abdhage.rentail.model.AccommodationBooking;
import com.abdhage.rentail.model.ids.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<AccommodationBooking, BookingId> {
}