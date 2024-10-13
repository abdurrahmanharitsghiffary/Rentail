package com.abdhage.rentail.booking;

import com.abdhage.rentail.booking.model.Booking;
import com.abdhage.rentail.booking.model.BookingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, BookingId> {
}