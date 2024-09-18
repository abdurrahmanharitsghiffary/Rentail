package com.abdhage.rentail.features.booking;

import com.abdhage.rentail.features.booking.model.AccommodationBooking;
import com.abdhage.rentail.features.booking.model.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<AccommodationBooking, BookingId> {
}